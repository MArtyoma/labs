import java.util.Scanner;

public class WordCounter {

  public static int countWords(String text) {
    if (text == null || text.isEmpty()) {
      return 0;
    }

    String trimmedText = text.trim();
    if (trimmedText.isEmpty()) {
      return 0;
    }

    String[] words = trimmedText.split("\\s+");
    return words.length;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    scanner.close();

    System.out.println(countWords(input));
  }
}
