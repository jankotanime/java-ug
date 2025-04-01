import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Basket {
  private final List<BasketPosition> list;
  private final List<Promotion> promotions;
  private double totalPrice;
  private double discountedPrice;
  private ArrayList<Product> shop;

  public Basket(ArrayList<Product> shop) {
    this.list = new ArrayList<>();
    this.promotions = new ArrayList<>();
    this.totalPrice = 0.0;
    this.discountedPrice = 0.0;
    this.shop = shop;
  }

  public List<BasketPosition> getList() {
    return list;
  }

  public void addPromotion(Promotion promotion) {
    this.promotions.add(promotion);
  }

  public void addToBasket(Product product) {
    for (BasketPosition p : this.list) {
        if (p.getProduct().equals(product)) {
            p.increaseAmount();
            recalculatePrices();
            return;
        }
    }
    this.list.add(new BasketPosition(product));
    recalculatePrices();
    sortBasket();
  }

  private void recalculatePrices() {
    this.totalPrice = list.stream().mapToDouble(p -> p.getFullPrice(false)).sum();
    this.discountedPrice = totalPrice;
    System.out.println(this.discountedPrice);
    for (Promotion promotion : promotions) {
      promotion.apply(this, shop);
    }
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public double getDiscountPrice() {
    return discountedPrice;
  }

  public void setDiscountedPrice(Double price) {
    this.discountedPrice = Math.round(price*100)/100.0;
  }

  private void sortBasket() {
    this.list.sort(Comparator.comparingDouble((BasketPosition p) -> -p.getFullPrice(false)).reversed().thenComparing(p -> p.getName()));
  }

  public int getTotalQuantity() {
    return list.stream().mapToInt(BasketPosition::getAmount).sum();
  }

  public void addFreeProduct(Product product) {
    this.list.add(new BasketPosition(product));
  }

  public void printAllProducts() {
    System.out.println("----------------");
    recalculatePrices();
    System.out.println("Total Price: " + getTotalPrice());
    System.out.println("Total Price with discount: " + getDiscountPrice());
    for (BasketPosition p : list) {
      System.out.println("Product: " + p.getProduct().getName() + ", Quantity: " + p.getAmount() + ", Price: " + p.getFullPrice(false));
    }
  }
}