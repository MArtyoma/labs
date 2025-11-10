import java.util.*;

// Вспомнилась задача из собесов про FizzBuzz :)

interface GradingStrategy {
  String getGrade(int score);

  boolean isValid(int score);

  String getName();
}


abstract class AbstractGradingStrategy implements GradingStrategy {
  protected final int minScore;
  protected final int maxScore;
  protected final List<GradeThreshold> thresholds;

  protected AbstractGradingStrategy(int minScore, int maxScore,
      List<GradeThreshold> thresholds) {
    this.minScore = minScore;
    this.maxScore = maxScore;
    this.thresholds = new ArrayList<>(thresholds);
    this.thresholds.sort((a, b) -> Integer.compare(b.threshold, a.threshold));
  }

  @Override
  public boolean isValid(int score) {
    return score >= minScore && score <= maxScore;
  }

  @Override
  public String getGrade(int score) {
    for (GradeThreshold th : thresholds) {
      if (score >= th.threshold) {
        return th.label;
      }
    }
    return thresholds.isEmpty() ? "F"
        : thresholds.get(thresholds.size() - 1).label;
  }

  protected static class GradeThreshold {
    final int threshold;
    final String label;

    GradeThreshold(int threshold, String label) {
      this.threshold = threshold;
      this.label = label;
    }
  }
}


class USGradingStrategy extends AbstractGradingStrategy {
  public USGradingStrategy() {
    super(0, 100,
        Arrays.asList(new GradeThreshold(90, "A"), new GradeThreshold(80, "B"),
            new GradeThreshold(70, "C"), new GradeThreshold(60, "D")));
  }

  @Override
  public String getName() {
    return "US Grading";
  }

  @Override
  public String getGrade(int score) {
    if (!isValid(score))
      return "Invalid";
    for (GradeThreshold th : thresholds) {
      if (score >= th.threshold) {
        return th.label;
      }
    }
    return "F";
  }
}


class GradeEvaluator {
  private final GradingStrategy strategy;

  public GradeEvaluator(GradingStrategy strategy) {
    this.strategy =
        Objects.requireNonNull(strategy, "Strategy must not be null");
  }

  public String evaluate(int score) {
    if (!strategy.isValid(score)) {
      return "Invalid";
    }
    return strategy.getGrade(score);
  }
}


class GradingStrategyFactory {
  private static final Map<String, GradingStrategy> strategies =
      new HashMap<>();

  static {
    registerStrategy("US", new USGradingStrategy());
  }

  public static void registerStrategy(String key, GradingStrategy strategy) {
    strategies.put(key, strategy);
  }

  public static GradingStrategy getStrategy(String key) {
    GradingStrategy strategy = strategies.get(key);
    if (strategy == null) {
      throw new IllegalArgumentException("Unknown grading strategy: " + key);
    }
    return strategy;
  }

  public static Set<String> getAvailableStrategies() {
    return strategies.keySet();
  }
}


public class Score {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int score = scanner.nextInt();
    scanner.close();

    GradingStrategy strategy = GradingStrategyFactory.getStrategy("US");

    GradeEvaluator evaluator = new GradeEvaluator(strategy);
    String result = evaluator.evaluate(score);

    System.out.println(result);

    scanner.close();
  }
}
