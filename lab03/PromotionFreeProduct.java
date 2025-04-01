import java.util.ArrayList;

class PromotionFreeProduct extends Promotion {
  private final double threshold;
  private final String freeProductCode;

  public PromotionFreeProduct() {
      this.threshold = 100.0;
      this.freeProductCode = "a1";
  }

  @Override
  public void apply(Basket basket, ArrayList<Product> shop) {
      if (basket.getTotalPrice() >= threshold) {
            System.out.println("Dostępny darmowy produkt z kodem " + freeProductCode);
      }
  }
}
