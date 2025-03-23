public class Product {
  private String code;
  private String name;
  private double price;
  private double d_price;

  public Product(String code, String name, double price) {
    this.code = code;
    this.name = name;
    this.price = price;
    this.d_price = Double.valueOf(String.format("%.2f", (price*95.0)/100.0));
  }

  public double getPrice(boolean d) {
    if (d == true) {
      return this.d_price;
    } else {
      return this.price;
    }
  } 

  public String getName() {
    return this.name;
  } 

  public String getCode() {
    return this.code;
  }
}
