public class ArrayAverage {
  public static void main(String[] args) {
    // "abc" вызовет исключение
    String[] arr = {"1", "2", "3", "4", "5", "abc"};
    int sum = 0;
    int count = 0;
    try {
      // Умышленная ошибка: i <= arr.length
      for (int i = 0; i <= arr.length; i++) {
        int num = Integer.parseInt(arr[i]);
        sum += num;
        count++;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Ошибка: выход за границы массива.");
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.out.println("Ошибка: элемент массива не является числом.");
      e.printStackTrace();
    } finally {
      if (count > 0) {
        double average = (double) sum / count;
        System.out.println("Среднее арифметическое: " + average);
      } else {
        System.out
            .println("Невозможно вычислить среднее: нет корректных данных.");
      }
    }
  }
}
