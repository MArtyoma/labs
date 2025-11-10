import java.util.stream.Stream;

public class PrefixAdder {
  @DataProcessor(name = "С префиксом")
  public Stream<String> addPrefix(Stream<String> input) {
    return input.map(s -> "PROCESSED: " + s);
  }
}
