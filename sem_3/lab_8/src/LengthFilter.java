import java.util.stream.Stream;

public class LengthFilter {
  @DataProcessor(name = "Длинные строки")
  public Stream<String> filterLongStrings(Stream<String> input) {
    return input.filter(s -> s.length() > 5);
  }
}
