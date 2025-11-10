import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Merger {

  public static int[] mergeUniqueSorted(List<List<Integer>> items) {
    Set<Integer> uniqueNumbers = new HashSet<>();

    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < items.get(i).size(); j++) {
        uniqueNumbers.add(items.get(i).get(j));
      }
    }

    int[] result = uniqueNumbers.stream().mapToInt(Integer::intValue).toArray();
    Arrays.sort(result);

    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();

    Pattern pattern = Pattern.compile("\\[.*?\\]");
    Matcher matcher = pattern.matcher(input);
    Pattern patternDiget = Pattern.compile("-?\\d+");

    List<List<Integer>> matrix = new ArrayList<>();

    while (matcher.find()) {
      matrix.add(new ArrayList<>());
      Matcher matcherDiget = patternDiget.matcher(matcher.group());

      while (matcherDiget.find()) {
        matrix.getLast().add(Integer.parseInt(matcherDiget.group()));
      }
    }

    int[] result = mergeUniqueSorted(matrix);

    System.out.print("[");
    for (int i = 0; i < result.length; i++) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(result[i]);
    }
    System.out.println("]");

  }
}
