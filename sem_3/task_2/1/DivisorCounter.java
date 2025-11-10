import java.util.Scanner;

public class DivisorCounter {
  public static int countDivisors(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Число должно быть положительным.");
    }

    int count = 0;
    for (int i = 1; i * i <= n; i++) {
      if (n % i == 0) {
        if (i * i == n) {
          count++;
        } else {
          count += 2;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();

    try {
      int result = countDivisors(n);
      System.out.println(result);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

    scanner.close();
  }
}
