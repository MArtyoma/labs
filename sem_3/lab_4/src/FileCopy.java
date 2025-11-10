import java.io.*;

public class FileCopy {
  public static void main(String[] args) {
    String sourceFile = "source.txt";
    String destFile = "destination.txt";
    FileInputStream fis = null;
    FileOutputStream fos = null;
    try {
      fis = new FileInputStream(sourceFile);
      fos = new FileOutputStream(destFile);
      int byteData;
      while ((byteData = fis.read()) != -1) {
        fos.write(byteData);
      }
      System.out.println("Файл успешно скопирован.");
    } catch (FileNotFoundException e) {
      System.out.println("Ошибка: файл не найден.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Ошибка при чтении или записи файла.");
      e.printStackTrace();
    } finally {
      try {
        if (fis != null)
          fis.close();
        if (fos != null)
          fos.close();
      } catch (IOException e) {
        System.out.println("Ошибка при закрытии файлов.");
        e.printStackTrace();
      }
    }
  }
}
