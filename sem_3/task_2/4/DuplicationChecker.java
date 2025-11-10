import java.util.Scanner;

public class DuplicationChecker {
  // У нас четко не прописано что нужно именно инты делать :)
  // Конечно есть пример hasDuplicates([1, 2, 3, 4]), но мы сделаем круче
  // Так что сделал в общем случаи для строк
  // Здорово если вы читаете этот коммент! <3

  public static boolean checker(String[] items) {
    // Брутфорсим!
    for (int i = 0; i < items.length; i++) {
      for (int j = i + 1; j < items.length; j++) {
        if (items[i].equals(items[j])) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine().trim();
    scanner.close();

    String[] parts = input.split(",");
    for (int i = 0; i < parts.length; i++) {
      parts[i] = parts[i].trim();
    }

    System.out.println(checker(parts));
  }
}
