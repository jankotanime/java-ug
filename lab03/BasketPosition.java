class BasketPosition {
  private final Product product;
  private int amount;
  private double discountRate;

  public BasketPosition(Product product) {
    this.product = product;
    this.amount = 1;
    this.discountRate = 0.0;
  }

  public String getName() {
    return product.getName();
  }

  public String getCode() {
    return product.getCode();
  }

  public double getFullPrice(boolean discount) {
    double price = this.product.getPrice(discount) * this.amount;
    return price * (1 - discountRate);
  }

  public int getAmount() {
    return this.amount;
  }

  public void increaseAmount() {
    this.amount++;
  }

  public void applyDiscount(double discountRate) {
    this.discountRate = discountRate;
  }

  public Product getProduct() {
    return product;
  }
}