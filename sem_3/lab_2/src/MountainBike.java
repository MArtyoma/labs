class MountainBike extends Bicycle {
  private boolean hasSuspension;

  public MountainBike() {
    super();
    this.hasSuspension = false;
  }

  public MountainBike(String model, int yearOfManufacture, boolean hasBasket,
      boolean hasSuspension) {
    super(model, yearOfManufacture, hasBasket);
    this.hasSuspension = hasSuspension;
  }

  public boolean isHasSuspension() {
    return hasSuspension;
  }

  public void setHasSuspension(boolean hasSuspension) {
    this.hasSuspension = hasSuspension;
  }

  @Override
  public String getDetails() {
    return "Mountain Bike: Model=" + getModel() + ", Year of Manufacture="
        + getYearOfManufacture() + ", Has Basket=" + isHasBasket()
        + ", Has Suspension=" + isHasSuspension();
  }
}
