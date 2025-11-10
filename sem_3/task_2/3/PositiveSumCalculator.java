import java.util.Scanner;

public class PositiveSumCalculator {

  public static int sumOfPositives(int[] array) {
    int sum = 0;
    for (int num : array) {
      if (num > 0) {
        sum += num;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine().trim();

    String[] parts = input.split(",");

    scanner.close();

    int sum = 0;

    try {
      for (int i = 0; i < parts.length; i++) {
        int item = Integer.parseInt(parts[i].trim());

        if (item > 0) {
          sum += item;
        }
      }
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

    System.out.println(sum);
  }
}
