import java.util.Scanner;

public class Palindrome {

  public static void main(String[] args) {
    String str = "not empty";
    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < args.length; i++) {
      try {
        checkString(args[i]);
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    while (!str.equals("0")) {
      System.out.print("Введите строку: ");

      try {
        str = scanner.next();
        checkString(str);

      } catch (Exception e) {
        System.out.println("Ошибка!\n" + e);
        scanner.nextLine();
      }
    }

    scanner.close();
  }

  /**
   * Делает все необходимые проверки и выводит результат
   * в консоль
   *
   * @param str строка для проверки
   */
  public static void checkString(String str) {
    if (str.equals("0"))
      return;

    boolean isPalindrome = isPalindrome(str);

    if (isPalindrome) {
      System.out.println("Палиндром: " + str);
    } else {
      System.out.print("Не палиндром: " + str + " ");
      System.out.println(reverseString(str));
    }
  }

  /**
   * Переворачивает строку
   * 
   * @param str входная строка
   * @return возвращает перевернутую строку
   */
  public static String reverseString(String str) {
    int lng = str.length();
    StringBuilder sb = new StringBuilder(str);
    sb.setLength(lng);
    for (int i = lng - 1; i >= 0; i--) {
      sb.setCharAt(lng - i - 1, str.charAt(i));
    }

    return sb.toString();
  }

  /**
   * Проверяет является ли строка Палиндромом
   *
   * @param str входная строка
   * @return true если строка Палиндром, false если нет
   */
  public static boolean isPalindrome(String str) {
    int lng = str.length();
    for (int i = 0; i < lng / 2; i++) {
      if (str.charAt(i) != str.charAt(lng - i - 1))
        return false;
    }

    return true;
  }
}
