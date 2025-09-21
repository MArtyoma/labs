import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {

  // Тут возможно использование float из за операции деления
  // Но по заданию в выводе целое число
  public static int toFahrenheit(int celsius) {
    return celsius * 9 / 5 + 32;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int celsius = scanner.nextInt();
      int fahrenheit = toFahrenheit(celsius);

      System.out.println(fahrenheit);
    } catch (InputMismatchException error) {
      System.out.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
