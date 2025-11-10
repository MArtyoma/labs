import java.util.regex.*;

public class WordFinder {
  public static void findWords(String text, char letter) {
    String regex = "\\b" + letter + "[a-zA-Z]*";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      System.out.println("Найдено слово: " + matcher.group());
    }
  }

  public static void main(String[] args) {
    String text = "Apple banana cherry Applepie";
    findWords(text, 'a');
  }
}
