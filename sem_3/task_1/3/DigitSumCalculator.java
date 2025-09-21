import java.util.Scanner;

public class DigitSumCalculator {
  public static int digitSum(int number) {
    number = Math.abs(number);

    int sum = 0;

    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }

    return sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int inputNumber = scanner.nextInt();
      int result = digitSum(inputNumber);

      System.out.println(result);
    } catch (Exception e) {
    } finally {
      scanner.close();
    }
  }
}
