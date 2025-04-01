import java.util.ArrayList;
import java.util.Comparator;

class Promotion3Products extends Promotion {
  @Override
  public void apply(Basket basket, ArrayList<Product> shop) {
    if (basket.getTotalQuantity() >= 3) {
      Product cheapest = shop.stream()
        .min(Comparator.comparingDouble(p -> p.getPrice(false)))
        .orElse(null);
      if (cheapest != null) {
        System.out.println("Dostępny darmowy najtańszy produkt");
      }
    }
  }
}