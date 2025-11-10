import java.util.*;

public class SalesTracker {
  private Map<String, Integer> sales;

  public SalesTracker() {
    sales = new HashMap<>();
  }

  public void addSale(String product) {
    sales.put(product, sales.getOrDefault(product, 0) + 1);
  }

  public void printSales() {
    System.out.println("Список проданных товаров:");
    for (Map.Entry<String, Integer> entry : sales.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() + " шт.");
    }
  }

  public int getTotalSales() {
    int total = 0;
    for (int count : sales.values()) {
      total += count;
    }
    return total;
  }

  public String getMostPopularProduct() {
    String mostPopular = null;
    int maxCount = 0;

    for (Map.Entry<String, Integer> entry : sales.entrySet()) {
      if (entry.getValue() > maxCount) {
        maxCount = entry.getValue();
        mostPopular = entry.getKey();
      }
    }

    return mostPopular;
  }

  public static void main(String[] args) {
    SalesTracker tracker = new SalesTracker();

    // Добавляем продажи
    tracker.addSale("Яблоки");
    tracker.addSale("Бананы");
    tracker.addSale("Яблоки");
    tracker.addSale("Апельсины");
    tracker.addSale("Бананы");
    tracker.addSale("Яблоки");

    // Выводим информацию о продажах
    tracker.printSales();
    System.out.println("Общее количество продаж: " + tracker.getTotalSales());
    System.out
        .println("Самый популярный товар: " + tracker.getMostPopularProduct());
  }
}
