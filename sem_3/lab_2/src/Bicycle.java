import java.util.HashMap;
import java.util.Map;

abstract class Bicycle {
  private String model = "Unknown";
  private int yearOfManufacture = 0;
  private boolean hasBasket = false;
  private static final Map<Class<?>, Integer> instanceCount = new HashMap<>();

  public Bicycle() {
    Class<?> clazz = getClass();
    instanceCount.put(clazz, instanceCount.getOrDefault(clazz, 0) + 1);
  }

  public Bicycle(String model, int yearOfManufacture, boolean hasBasket) {
    this();
    this.model = model;
    this.yearOfManufacture = yearOfManufacture;
    this.hasBasket = hasBasket;
  }

  public static <T> int getInstanceCount(Class<T> clazz) {
    return instanceCount.getOrDefault(clazz, 0);
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYearOfManufacture() {
    return yearOfManufacture;
  }

  public void setYearOfManufacture(int yearOfManufacture) {
    this.yearOfManufacture = yearOfManufacture;
  }

  public boolean isHasBasket() {
    return hasBasket;
  }

  public void setHasBasket(boolean hasBasket) {
    this.hasBasket = hasBasket;
  }

  public abstract String getDetails();

}
