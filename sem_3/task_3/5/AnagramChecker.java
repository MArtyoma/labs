import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramChecker {

  public static boolean isAnagram(String str1, String str2) {
    String cleanedStr1 = str1.replaceAll("\\s+", "").toLowerCase();
    String cleanedStr2 = str2.replaceAll("\\s+", "").toLowerCase();

    if (cleanedStr1.length() != cleanedStr2.length()) {
      return false;
    }

    char[] charArray1 = cleanedStr1.toCharArray();
    char[] charArray2 = cleanedStr2.toCharArray();

    Arrays.sort(charArray1);
    Arrays.sort(charArray2);

    return Arrays.equals(charArray1, charArray2);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    scanner.close();

    Pattern pattern = Pattern.compile("(?i)\\b\\w+\\b");
    Matcher matcher = pattern.matcher(input);

    List<String> myArray = new ArrayList<>();

    while (matcher.find()) {
      myArray.add(matcher.group());
    }

    if (myArray.size() < 2) {
      System.out.println(false);

      return;
    }

    System.out.println(isAnagram(myArray.get(0), myArray.get(1)));
  }
}
