class Product {
  private final String code;
  private final String name;
  private final double price;
  
  public Product(String code, String name, double price) {
      this.code = code;
      this.name = name;
      this.price = price;
  }
  
  public double getPrice(boolean discount) {
      return price;
  }

  public String getName() {
    return name;
  }
  
  public String getCode() {
      return code;
  }
}
