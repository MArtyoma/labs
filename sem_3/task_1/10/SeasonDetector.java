import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SeasonDetector {
  private static final Map<Integer, String> seasonMap = new HashMap<>();

  private static final String winter = "зима";
  private static final String spring = "весна";
  private static final String summer = "лето";
  private static final String autumn = "осень";

  static {
    seasonMap.put(1, winter);
    seasonMap.put(2, winter);
    seasonMap.put(12, winter);

    seasonMap.put(3, spring);
    seasonMap.put(4, spring);
    seasonMap.put(5, spring);

    seasonMap.put(6, summer);
    seasonMap.put(7, summer);
    seasonMap.put(8, summer);

    seasonMap.put(9, autumn);
    seasonMap.put(10, autumn);
    seasonMap.put(11, autumn);
  }

  public static String season(int month) {
    return seasonMap.getOrDefault(month, "");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int month = scanner.nextInt();
      String result = season(month);

      if (result.equals("")) {
        return;
      }
      System.out.println(result);
    } catch (Exception e) {
      System.out.println("Ошибка ввода");
    } finally {
      scanner.close();
    }
  }
}
