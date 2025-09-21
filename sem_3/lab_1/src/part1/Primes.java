import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Primes {

  public static void main(String[] args) {
    Long number = 1L;
    Scanner scanner = new Scanner(System.in);

    for (int i = 1; i <= 100; i++) {
      boolean result = isPrime(i, 10);

      if (result) {
        System.out.print(i + " ");
      }
    }
    System.out.println("");

    while (number > 0L) {
      System.out.print("Введите число: ");

      try {
        number = scanner.nextLong();

        if (number > 0) {
          boolean result = isPrime(number, 10);

          if (result) {
            System.out.println(number + " - оно простое\n");
          } else {
            System.out.println(number + " - оно НЕ простое\n");
          }
        }
      } catch (InputMismatchException e) {
        System.out.println("Ошибка! Введите корректное целое число.\n");
        scanner.nextLine();
      }
    }
    scanner.close();
  }

  /**
   * Проверяет, является ли число простым с помощью теста Миллера-Рабина
   * 
   * @param number проверяемое число
   * @param iterations количество итераций (точность теста)
   * @return true, если число вероятно простое, false если составное
   */
  public static boolean isPrime(long number, int iterations) {
    if (number <= 2) {
      return number == 2;
    }
    if (number == 3) {
      return true;
    }

    long s = 0;
    long d = number - 1;
    while ((d & 1) == 0) {
      d >>= 1;
      s++;
    }

    Random generator = new Random();

    for (int i = 0; i < iterations; i++) {
      long a = 2 + (long) (generator.nextDouble() * (number - 3));

      BigInteger bigA = BigInteger.valueOf(a);
      BigInteger bigN = BigInteger.valueOf(number);
      BigInteger x = bigA.modPow(BigInteger.valueOf(d), bigN);

      if (x.equals(BigInteger.ONE) || x.equals(bigN.subtract(BigInteger.ONE))) {
        continue;
      }

      boolean composite = true;
      for (long r = 1; r < s; r++) {
        x = x.modPow(BigInteger.valueOf(2), bigN);
        if (x.equals(BigInteger.ONE)) {
          return false;
        }
        if (x.equals(bigN.subtract(BigInteger.ONE))) {
          composite = false;
          break;
        }
      }
      if (composite) {
        return false;
      }
    }
    return true;
  }
}
