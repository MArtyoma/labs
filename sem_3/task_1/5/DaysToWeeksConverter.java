import java.util.Scanner;

public class DaysToWeeksConverter {
  public static String daysToWeeks(int days) {
    int weeks = days / 7;
    int remainingDays = days % 7;

    return weeks + " " + getWeekForm(weeks) +
        " и " + remainingDays + " " + getDayForm(remainingDays);
  }

  private static String getWeekForm(int num) {
    if (num % 10 == 1 && num % 100 != 11) {
      return "неделя";
    } else if ((num % 10 >= 2 && num % 10 <= 4) &&
        !(num % 100 >= 12 && num % 100 <= 14)) {
      return "недели";
    } else {
      return "недель";
    }
  }

  private static String getDayForm(int num) {
    if (num % 10 == 1 && num % 100 != 11) {
      return "день";
    } else if ((num % 10 >= 2 && num % 10 <= 4) &&
        !(num % 100 >= 12 && num % 100 <= 14)) {
      return "дня";
    } else {
      return "дней";
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int days = scanner.nextInt();
      String result = daysToWeeks(days);
      System.out.println(result);
    } catch (Exception e) {
    } finally {
      scanner.close();
    }
  }
}
