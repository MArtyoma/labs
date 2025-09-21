import java.util.Scanner;

public class RectangleAreaCalculator {
  public static double rectangleArea(double length, double width) {
    return length * width;
  }

  // Из за того что в примере где-то встречается вывод с float\double
  // приходится делать проверку является ли число целым числом чтобы
  // вывод соответствовал заданию
  // rectangleArea(4, 5) ➞ 20
  // rectangleArea(7, 2.5) ➞ 17.5
  // rectangleArea(10, 10) ➞ 100
  public static boolean isInteger(double value) {
    return value % 1 == 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      String input = scanner.nextLine();

      String[] parts = input.split(" ");

      double length = Double.parseDouble(parts[0]);
      double width = Double.parseDouble(parts[1]);

      double area = rectangleArea(length, width);

      if (isInteger(area)) {
        System.out.println((int) area);
      } else {
        System.out.println(area);
      }
    } catch (Exception e) {
      System.out.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
