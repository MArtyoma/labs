import java.util.Scanner;

public class RangeSum {

  public static long sumRange(int a, int b) {
    long n = (long) b - a + 1;
    return n * (a + b) / 2;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine().trim();

    String[] parts = input.split(",");
    int a = Integer.parseInt(parts[0].trim());
    int b = Integer.parseInt(parts[1].trim());

    long result = sumRange(a, b);
    System.out.println(result);

    scanner.close();
  }
}
