public class Stack<T> {
  private T[] data;
  private int size;

  public static void main(String[] args) {
    Stack<Integer> intStack = new Stack<>(5);

    intStack.push(10);
    intStack.push(20);
    intStack.push(30);
    System.out.println("Размер стека: " + intStack.size());

    System.out.println("Верхний элемент: " + intStack.peek());

    System.out.println("pop(): " + intStack.pop());
    System.out.println("pop(): " + intStack.pop());
    System.out.println("Размер стека после извлечения: " + intStack.size());

    intStack.push(40);
    intStack.push(50);
    System.out.println("Размер стека после добавления: " + intStack.size());
    System.out.println("Верхний элемент: " + intStack.peek());

    System.out.println("Стек пустой? " + intStack.isEmpty());

    while (!intStack.isEmpty()) {
      System.out.println("pop(): " + intStack.pop());
    }
    System.out.println("Стек пустой? " + intStack.isEmpty());
  }

  @SuppressWarnings("unchecked")
  public Stack(int capacity) {
    data = (T[]) new Object[capacity];
    size = 0;
  }

  public void push(T element) {
    if (size >= data.length) {
      throw new IllegalStateException("Стек переполнен");
    }
    data[size++] = element;
  }

  public T pop() {
    if (size == 0) {
      throw new IllegalStateException("Стек пуст");
    }
    T element = data[--size];
    data[size] = null;
    return element;
  }

  public T peek() {
    if (size == 0) {
      throw new IllegalStateException("Стек пуст");
    }
    return data[size - 1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
}
