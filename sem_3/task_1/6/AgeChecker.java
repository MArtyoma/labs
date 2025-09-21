import java.util.Scanner;

public class AgeChecker {
  public static String checkAge(int age) {
    if (age >= 18) {
      return "совершеннолетний";
    }
    return "несовершеннолетний";
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int age = scanner.nextInt();
      String result = checkAge(age);

      System.out.println(result);
    } catch (Exception e) {
      System.out.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
