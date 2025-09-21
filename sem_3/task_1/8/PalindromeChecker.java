import java.util.Scanner;

public class PalindromeChecker {
  public static boolean isPalindrome(String str) {
    int lng = str.length();
    for (int i = 0; i < lng / 2; i++) {
      if (str.charAt(i) != str.charAt(lng - i - 1))
        return false;
    }

    return true;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      String input = scanner.nextLine();

      if (input == null || input.trim().isEmpty()) {
        throw new IllegalArgumentException("Строка не может быть пустой");
      }

      boolean result = isPalindrome(input);
      System.out.println(result);

    } catch (Exception e) {
      System.err.println("Ошибка ввода");

    } finally {
      scanner.close();
    }
  }
}
