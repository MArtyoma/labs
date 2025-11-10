import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ArraySumCalculator {
  private static final int ARRAY_SIZE = 1000;
  private static final int[] array = new int[ARRAY_SIZE];

  static {
    for (int i = 0; i < ARRAY_SIZE; i++) {
      array[i] = (int) (Math.random() * 100);
    }
  }

  public static int calculateSum()
      throws InterruptedException, ExecutionException {
    int mid = ARRAY_SIZE / 2;

    CompletableFuture<Integer> firstHalfFuture =
        CompletableFuture.supplyAsync(() -> {
          int sum = 0;
          for (int i = 0; i < mid; i++) {
            sum += array[i];
          }
          System.out.println("Первая половина обработана: " + sum);
          return sum;
        });

    CompletableFuture<Integer> secondHalfFuture =
        CompletableFuture.supplyAsync(() -> {
          int sum = 0;
          for (int i = mid; i < ARRAY_SIZE; i++) {
            sum += array[i];
          }
          System.out.println("Вторая половина обработана: " + sum);
          return sum;
        });

    CompletableFuture<Integer> totalSumFuture = firstHalfFuture
        .thenCombine(secondHalfFuture, (sum1, sum2) -> sum1 + sum2);

    return totalSumFuture.get();
  }

  public static void main(String[] args) {
    try {
      int totalSum = calculateSum();
      System.out.println("Общая сумма элементов массива: " + totalSum);

      int verificationSum = 0;
      for (int value : array) {
        verificationSum += value;
      }
      System.out.println("Сумма для проверки: " + verificationSum);
      System.out
          .println("Результаты совпадают: " + (totalSum == verificationSum));
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
