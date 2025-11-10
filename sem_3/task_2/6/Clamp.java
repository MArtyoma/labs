import java.util.Scanner;

public class Clamp {

  public static int clampValue(int value, int min, int max) {
    if (value < min) {
      return min;
    } else if (value > max) {
      return max;
    } else {
      return value;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine().trim();
    scanner.close();

    String[] elements = input.split(",\\s*");

    if (elements.length < 3) {
      return;
    }

    int[] valMinMaxArr = new int[3];

    for (int i = 0; i < 3; i++) {
      valMinMaxArr[i] = Integer.parseInt(elements[i]);
    }

    int result = clampValue(valMinMaxArr[0], valMinMaxArr[1], valMinMaxArr[2]);

    System.out.println(result);
  }
}
