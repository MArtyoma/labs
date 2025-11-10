import java.util.Scanner;

public class ShiftArray {
  public static String[] rotateRight(String[] items, int delta) {
    String[] shifted = new String[items.length];

    if (delta < 0) {
      delta = items.length - (Math.abs(delta) % items.length);
    }

    for (int i = 0; i < items.length; i++) {
      shifted[(i + delta) % items.length] = items[i];
    }

    return shifted;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine().trim();
    scanner.close();

    int lastCommaIndex = input.lastIndexOf(",");
    if (lastCommaIndex == -1) {
      return;
    }

    String arrayPart = input.substring(0, lastCommaIndex).trim();
    String numberPart = input.substring(lastCommaIndex + 1).trim();

    if (arrayPart.startsWith("[") && arrayPart.endsWith("]")) {
      arrayPart = arrayPart.substring(1, arrayPart.length() - 1);
    }

    String[] elements = arrayPart.split(",\\s*");
    int shiftNumber = Integer.parseInt(numberPart.trim());

    String[] shifted = rotateRight(elements, shiftNumber);

    System.out.print("[");
    for (int i = 0; i < shifted.length; i++) {
      if (i > 0) {
        System.out.print(", ");
      }
      System.out.print(shifted[i]);
    }
    System.out.println("]");
  }
}
