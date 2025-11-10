import java.util.Scanner;

public class DigitSumCalculator {

  public static int sumOfDigits(int number) {
    int num = Math.abs(number);
    int sum = 0;

    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }

    return sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int inputNumber = scanner.nextInt();
    scanner.close();

    System.out.println(sumOfDigits(inputNumber));
  }
}
