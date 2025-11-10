import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomStack<T> {
  private ArrayList<T> stack = new ArrayList<>();

  public void push(T item) {
    stack.add(item);
  }

  public T pop() throws CustomEmptyStackException {
    if (stack.isEmpty()) {
      logException("Попытка извлечь элемент из пустого стека");
      throw new CustomEmptyStackException("Стек пуст!");
    }
    return stack.remove(stack.size() - 1);
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  private void logException(String message) {
    try (FileWriter writer = new FileWriter("exceptions.log", true)) {
      writer.write(LocalDateTime.now() + " - " + message + "\n");
    } catch (IOException e) {
      System.err.println("Не удалось записать в лог: " + e.getMessage());
    }
  }
}
