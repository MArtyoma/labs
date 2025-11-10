import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriangleChecker {

  public static boolean isTriangle(double a, double b, double c) {
    return (a + b > c) && (a + c > b) && (b + c > a);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    scanner.close();

    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(input);

    List<Integer> myArray = new ArrayList<>();

    while (matcher.find()) {
      myArray.add(Integer.parseInt(matcher.group()));
    }

    if (myArray.size() < 3)
      return;

    System.out
        .println(isTriangle(myArray.get(0), myArray.get(1), myArray.get(2)));
  }
}
