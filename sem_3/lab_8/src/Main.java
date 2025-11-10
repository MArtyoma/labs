import java.io.*;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    try (
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
      writer.write("apple");
      writer.newLine();
      writer.write("banana");
      writer.newLine();
      writer.write("cat");
      writer.newLine();
      writer.write("elephant");
    } catch (IOException e) {
      System.err.println("Ошибка создания входного файла: " + e.getMessage());
      return;
    }

    DataManager manager = new DataManager();

    manager.registerDataProcessor(new LengthFilter());
    manager.registerDataProcessor(new UpperCaseTransformer());
    manager.registerDataProcessor(new PrefixAdder());

    manager.loadData(inputFile);
    manager.processData();
    manager.saveData(outputFile);

    manager.shutdown();

    System.out
        .println("Обработка завершена. Результат сохранён в " + outputFile);
  }
}
