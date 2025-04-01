import java.util.ArrayList;

class PromotionProductDiscount extends Promotion {
  private final String productCode;
  private final double discount;

  public PromotionProductDiscount() {
      this.productCode = "a1";
      this.discount = 0.05;
  }

  @Override
  public void apply(Basket basket, ArrayList<Product> shop) {
    for (BasketPosition product : basket.getList()) {
      if (product.getCode() == productCode) {
        basket.setDiscountedPrice(basket.getDiscountPrice()*(1-discount));
      }
    }
  }
}