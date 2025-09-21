import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class IsPrimeChecker {
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

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int input = scanner.nextInt();

      boolean result = isPrime(input, 10);
      System.out.println(result);

    } catch (Exception e) {
      System.err.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
