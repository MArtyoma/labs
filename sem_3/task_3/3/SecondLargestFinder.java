import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondLargestFinder {

  public static int secondLargest(List<Integer> arr) {
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;

    for (int num : arr) {
      if (num > largest) {
        secondLargest = largest;
        largest = num;
      } else if (num > secondLargest && num != largest) {
        secondLargest = num;
      }
    }

    if (secondLargest == Integer.MIN_VALUE) {
      return largest;
    }

    return secondLargest;
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

    System.out.println(secondLargest(myArray));
  }
}
