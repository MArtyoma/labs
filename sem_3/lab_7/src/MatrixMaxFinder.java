import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MatrixMaxFinder {
  private static final int ROWS = 10;
  private static final int COLS = 10;
  private static final int[][] matrix = new int[ROWS][COLS];

  static {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        matrix[i][j] = (int) (Math.random() * 10000);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static int findMaxElement()
      throws InterruptedException, ExecutionException {
    CompletableFuture<Integer>[] rowFutures = new CompletableFuture[ROWS];

    for (int i = 0; i < ROWS; i++) {
      final int rowIndex = i;
      rowFutures[i] = CompletableFuture.supplyAsync(() -> {
        int rowMax = matrix[rowIndex][0];
        for (int j = 1; j < COLS; j++) {
          if (matrix[rowIndex][j] > rowMax) {
            rowMax = matrix[rowIndex][j];
          }
        }
        System.out.println("Максимум в строке " + rowIndex + ": " + rowMax);
        return rowMax;
      });
    }

    CompletableFuture<Integer> globalMaxFuture =
        CompletableFuture.completedFuture(Integer.MIN_VALUE);

    for (CompletableFuture<Integer> future : rowFutures) {
      globalMaxFuture = globalMaxFuture.thenCombine(future, Math::max);
    }

    return globalMaxFuture.get();
  }

  public static void main(String[] args) {
    try {
      int maxElement = findMaxElement();
      System.out.println("Наибольший элемент в матрице: " + maxElement);

      int verificationMax = matrix[0][0];
      for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
          if (matrix[i][j] > verificationMax) {
            verificationMax = matrix[i][j];
          }
        }
      }
      System.out.println("Максимум для проверки: " + verificationMax);
      System.out
          .println("Результаты совпадают: " + (maxElement == verificationMax));
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
