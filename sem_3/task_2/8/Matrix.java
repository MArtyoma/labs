import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matrix {

  public static int diagonalSum(List<List<Integer>> matrix) {
    int sum = 0;

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (i == j) {
          sum += matrix.get(i).get(j);
        }
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();

    if (!input.startsWith("[") || !input.endsWith("]")) {
      return;
    }
    input = input.substring(1, input.length() - 1);

    Pattern pattern = Pattern.compile("\\[.*?\\]");
    Matcher matcher = pattern.matcher(input);
    Pattern patternDiget = Pattern.compile("\\d");

    List<List<Integer>> matrix = new ArrayList<>();

    while (matcher.find()) {
      matrix.add(new ArrayList<>());
      Matcher matcherDiget = patternDiget.matcher(matcher.group());

      while (matcherDiget.find()) {
        matrix.getLast().add(Integer.parseInt(matcherDiget.group()));
      }
    }

    System.out.println(diagonalSum(matrix));
  }
}
