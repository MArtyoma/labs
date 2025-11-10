import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class WordReverser {

  public static String reverseWords(String input) {
    String[] words = input.split(" ");
    Collections.reverse(Arrays.asList(words));
    return String.join(" ", words);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();

    String result = reverseWords(input);
    System.out.println(result);

    scanner.close();
  }
}
