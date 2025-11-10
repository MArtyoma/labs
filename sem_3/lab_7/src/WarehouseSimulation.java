import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class WarehouseSimulation {

  static class Product {
    private final int id;
    private final int weight;

    public Product(int id, int weight) {
      this.id = id;
      this.weight = weight;
    }

    public int getId() {
      return id;
    }

    public int getWeight() {
      return weight;
    }

    @Override
    public String toString() {
      return "Товар{id=" + id + ", вес=" + weight + "кг}";
    }
  }

  static class Warehouse {
    private final ConcurrentLinkedQueue<Product> products;
    private final AtomicInteger totalWeight;

    public Warehouse() {
      this.products = new ConcurrentLinkedQueue<>();
      this.totalWeight = new AtomicInteger(0);
    }

    public void addProduct(Product product) {
      products.offer(product);
      totalWeight.addAndGet(product.getWeight());
    }

    public Product takeProduct() {
      Product product = products.poll();
      if (product != null) {
        totalWeight.addAndGet(-product.getWeight());
      }
      return product;
    }

    public boolean isEmpty() {
      return products.isEmpty();
    }

    public int getTotalWeight() {
      return totalWeight.get();
    }
  }

  static class Loader {
    private final int id;
    private int totalCarriedWeight;

    public Loader(int id) {
      this.id = id;
      this.totalCarriedWeight = 0;
    }

    public CompletableFuture<Integer> carryProducts(Warehouse source,
        int maxBatchWeight) {
      return CompletableFuture.supplyAsync(() -> {
        List<Product> currentBatch = new ArrayList<>();
        int currentWeight = 0;

        while (currentWeight < maxBatchWeight && !source.isEmpty()) {
          Product product = source.takeProduct();
          if (product != null
              && currentWeight + product.getWeight() <= maxBatchWeight) {
            currentBatch.add(product);
            currentWeight += product.getWeight();
            System.out.println("Грузчик " + id + " взял: " + product);
          } else if (product != null) {
            source.addProduct(product);
            break;
          }

          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
          }
        }

        totalCarriedWeight += currentWeight;
        System.out.println(
            "Грузчик " + id + " переносит партию весом " + currentWeight
                + "кг. Всего перенесено: " + totalCarriedWeight + "кг");

        return currentWeight;
      });
    }

    public int getTotalCarriedWeight() {
      return totalCarriedWeight;
    }
  }

  public static void main(String[] args) {
    Warehouse sourceWarehouse = new Warehouse();
    for (int i = 1; i <= 50; i++) {
      int weight = (int) (Math.random() * 30) + 5;
      sourceWarehouse.addProduct(new Product(i, weight));
    }

    System.out.println("Исходный склад загружен. Общий вес: "
        + sourceWarehouse.getTotalWeight() + "кг");

    Loader[] loaders = {new Loader(1), new Loader(2), new Loader(3)};

    final int MAX_BATCH_WEIGHT = 150;
    List<CompletableFuture<Integer>> allBatches = new ArrayList<>();

    while (!sourceWarehouse.isEmpty()) {
      List<CompletableFuture<Integer>> currentBatchFutures = new ArrayList<>();

      for (Loader loader : loaders) {
        if (!sourceWarehouse.isEmpty()) {
          currentBatchFutures
              .add(loader.carryProducts(sourceWarehouse, MAX_BATCH_WEIGHT));
        }
      }

      CompletableFuture<Void> currentCycle = CompletableFuture
          .allOf(currentBatchFutures.toArray(new CompletableFuture[0]));

      try {
        currentCycle.get();
        System.out.println("Партия доставлена на другой склад");
        System.out.println("Остаток на исходном складе: "
            + sourceWarehouse.getTotalWeight() + "кг");
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }

      allBatches.addAll(currentBatchFutures);
    }

    System.out.println("\nВсе готово :)");
    int totalTransferred = 0;
    for (Loader loader : loaders) {
      System.out.println("Грузчик " + loader.id + " перенес: "
          + loader.getTotalCarriedWeight() + "кг");
      totalTransferred += loader.getTotalCarriedWeight();
    }
    System.out.println("Общий перенесенный вес: " + totalTransferred + "кг");
  }
}
