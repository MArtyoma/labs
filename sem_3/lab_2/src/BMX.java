class BMX extends Bicycle {
  private boolean hasWheels;

  public BMX() {
    super();
    this.hasWheels = true;
  }

  public BMX(String model, int yearOfManufacture, boolean hasBasket,
      boolean hasWheels) {
    super(model, yearOfManufacture, hasBasket);
    this.hasWheels = hasWheels;
  }

  public boolean isHasWheels() {
    return hasWheels;
  }

  public void setHasWheels(boolean hasWheels) {
    this.hasWheels = hasWheels;
  }

  @Override
  public String getDetails() {
    return "BMX: Model=" + getModel() + ", Year of Manufacture="
        + getYearOfManufacture() + ", Has Basket=" + isHasBasket()
        + ", Has Wheels=" + isHasWheels();
  }
}
