import java.util.Scanner;

public class VowelCounter {

  public static int countVowels(String str) {
    if (str == null || str.isEmpty()) {
      return 0;
    }

    int count = 0;
    String lowerCaseStr = str.toLowerCase();

    for (int i = 0; i < lowerCaseStr.length(); i++) {
      char c = lowerCaseStr.charAt(i);
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    int vowelCount = countVowels(input);

    System.out.println(vowelCount);

    scanner.close();
  }
}
