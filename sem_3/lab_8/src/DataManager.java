import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataManager {
  private List<String> rawData = new ArrayList<>();
  private List<String> processedData = new ArrayList<>();
  private final List<Object> processors = new ArrayList<>();
  private final ExecutorService executor = Executors.newCachedThreadPool();

  public void registerDataProcessor(Object processor) {
    processors.add(processor);
  }

  public void loadData(String source) {
    try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
      rawData = reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      System.err.println("Ошибка загрузки данных: " + e.getMessage());
    }
  }

  public void processData() {
    List<CompletableFuture<List<String>>> futures = new ArrayList<>();

    for (Object processor : processors) {
      Class<?> clazz = processor.getClass();
      for (Method method : clazz.getDeclaredMethods()) {
        if (method.isAnnotationPresent(DataProcessor.class)) {
          System.out.println(method.getAnnotation(DataProcessor.class).name());
          CompletableFuture<List<String>> future =
              CompletableFuture.supplyAsync(() -> {
                try {
                  Stream<String> stream = (Stream<String>) method
                      .invoke(processor, rawData.stream());
                  return stream.collect(Collectors.toList());
                } catch (Exception e) {
                  System.err.println(
                      "Ошибка при вызове обработчика: " + e.getMessage());
                  return new ArrayList<String>();
                }
              }, executor);
          futures.add(future);
        }
      }
    }

    CompletableFuture<Void> allDone =
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    allDone.join();

    processedData = futures.stream().map(CompletableFuture::join)
        .flatMap(List::stream).distinct().collect(Collectors.toList());
  }

  public void saveData(String destination) {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(destination))) {
      for (String line : processedData) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Ошибка сохранения данных: " + e.getMessage());
    }
  }

  public void shutdown() {
    executor.shutdown();
    try {
      if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
  }
}
