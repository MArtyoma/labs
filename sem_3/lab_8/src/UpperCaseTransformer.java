import java.util.stream.Stream;

public class UpperCaseTransformer {
  @DataProcessor(name = "Верхний регистр")
  public Stream<String> toUpperCase(Stream<String> input) {
    return input.map(String::toUpperCase);
  }
}
