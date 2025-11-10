public class Test {

  private int counter = 0;

  public synchronized void increment() {
    counter++;
  }

  public int getCounter() {
    return counter;
  }

  public static void main(String[] args) throws InterruptedException {
    Test example = new Test();

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(example.getCounter());
  }
}
