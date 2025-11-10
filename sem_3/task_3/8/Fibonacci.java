import java.util.Scanner;

public class Fibonacci {

  public static int fibonacci(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int input = scanner.nextInt();
    scanner.close();

    if (input < 0) {
      System.out.println(0);
      return;
    }

    System.out.println(fibonacci(input));
  }
}
