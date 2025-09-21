class ChildrenBike extends Bicycle {
  private boolean hasBell;

  public ChildrenBike() {
    super();
    this.hasBell = false;
  }

  public ChildrenBike(String model, int yearOfManufacture, boolean hasBasket,
      boolean hasBell) {
    super(model, yearOfManufacture, hasBasket);
    this.hasBell = hasBell;
  }

  public boolean isHasBell() {
    return hasBell;
  }

  public void setHasBell(boolean hasBell) {
    this.hasBell = hasBell;
  }

  @Override
  public String getDetails() {
    return "Children's Bike: Model=" + getModel() + ", Year of Manufacture="
        + getYearOfManufacture() + ", Has Basket=" + isHasBasket()
        + ", Has Bell=" + isHasBell();
  }
}
