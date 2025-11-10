import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayDifference {

  public static int differenceMaxMin(List<Integer> array) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (Integer number : array) {
      if (number < min) {
        min = number;
      }
      if (number > max) {
        max = number;
      }
    }

    return Math.abs(max - min);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    scanner.close();

    Pattern pattern = Pattern.compile("-?\\d+");
    Matcher matcher = pattern.matcher(input);

    List<Integer> myArray = new ArrayList<>();

    while (matcher.find()) {
      myArray.add(Integer.parseInt(matcher.group()));
    }

    System.out.println(differenceMaxMin(myArray));
  }
}
