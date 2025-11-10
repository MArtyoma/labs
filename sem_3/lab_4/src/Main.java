public class Main {
  public static void main(String[] args) {
    CustomStack<Integer> stack = new CustomStack<>();
    try {
      stack.pop(); // Вызовет исключение
    } catch (CustomEmptyStackException e) {
      System.out.println("Перехвачено исключение: " + e.getMessage());
    }
    stack.push(10);
    stack.push(20);
    try {
      System.out.println("Извлечено: " + stack.pop());
      System.out.println("Извлечено: " + stack.pop());
      stack.pop(); // Снова пустой стек
    } catch (CustomEmptyStackException e) {
      System.out.println("Перехвачено исключение: " + e.getMessage());
    }
  }
}
