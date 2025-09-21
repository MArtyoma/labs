import java.util.Scanner;

public class EvenChecker {
  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int number = scanner.nextInt();

      boolean result = isEven(number);
      System.out.println(result);
    } catch (Exception e) {
    } finally {
      scanner.close();
    }
  }
}
