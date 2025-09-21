import java.util.Scanner;

public class AverageCalculator {
  public static boolean isInteger(double value) {
    return value % 1 == 0;
  }

  public static double average(double[] a) {
    if (a.length == 0)
      return 0;

    double sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
    }
    return sum / (double) a.length;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      String input = scanner.nextLine();
      String[] numbersStr = input.split(" ");

      double[] numbers = new double[numbersStr.length];

      for (int i = 0; i < numbersStr.length; i++) {
        numbers[i] = Double.parseDouble(numbersStr[i]);
      }

      double result = average(numbers);

      if (isInteger(result)) {
        System.out.println((int) result);
      } else {
        System.out.println(result);
      }

    } catch (Exception e) {
      System.out.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
