import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvenFilter {
  public static int[] filterEven(List<Integer> numbers) {
    List<Integer> evenNumbers = new ArrayList<>();

    for (int number : numbers) {
      if (number % 2 == 0) {
        evenNumbers.add(number);
      }
    }

    int[] result = new int[evenNumbers.size()];
    for (int i = 0; i < evenNumbers.size(); i++) {
      result[i] = evenNumbers.get(i);
    }

    return result;
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

    System.out.println(Arrays.toString(filterEven(myArray)));
  }
}
