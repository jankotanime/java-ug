import java.util.ArrayList;

class Promotion5Procent extends Promotion {
  private final double discountThreshold;

  public Promotion5Procent() {
      this.discountThreshold = 200.0;
  }

  @Override
  public void apply(Basket basket, ArrayList<Product> shop) {
      if (basket.getTotalPrice() > discountThreshold) {
        Double newPrice = basket.getTotalPrice() * (0.95);
        basket.setDiscountedPrice(newPrice);
      }
  }
}
