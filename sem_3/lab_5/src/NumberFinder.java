import java.util.regex.*;

public class NumberFinder {
  public static void main(String[] args) {
    String text = "The price is $19.99 and quantity is 5.";
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      System.out.println("Найдено число: " + matcher.group());
    }
  }
}
