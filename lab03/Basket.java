import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class Basket {
  private class BasketPosition {
    private Product product;
    private int amount;

    private BasketPosition(Product product) {
      this.product = product;
      this.amount = 1;
    }

    private double getFullPrice(boolean d) {
      return this.product.getPrice(d)*this.amount;
    }

    private int getAmount() {
      return this.amount;
    }

    private String getName() {
      return this.product.getName();
    }

    private String getCode() {
      return this.product.getCode();
    }
  }

  private ArrayList<BasketPosition> list;
  private Double discount_price = 100.0;
  private Double free_product = 200.0;
  private static class procentageDiscount {
    private static String code = "a1";
    private static int procentage = 20;
  }

  public Basket() {
    this.list = new ArrayList<BasketPosition>();
  }


  // Promocje

  private boolean isDiscount() {
    Double result = 0.0;
    for (BasketPosition p : this.list) {
      result += p.getFullPrice(false);
    }
    return (this.discount_price < Double.valueOf(String.format("%.2f", result)));
  }

  private boolean are3Products() {
    int result = 0;
    for (BasketPosition p : this.list) {
      result += p.getAmount();
    }
    return (3 < result);
  }
  
  private String getCheapestProduct(ArrayList<Product> shop) {
    Product cheapest = shop.get(0);
    for (Product p : shop) {
      if (cheapest.getPrice(false) > p.getPrice(false)) {
        cheapest = p;
      }
    }
    return cheapest.getName();
  }

  private String getMostExpensiveProduct(ArrayList<Product> shop) {
    Product expensive = shop.get(0);
    for (Product p : shop) {
      if (expensive.getPrice(false) < p.getPrice(false)) {
        expensive = p;
      }
    }
    return expensive.getName();
  }

  private Stream<Basket.BasketPosition> getMostExpepensiveProducts(int n) {
    return this.list.stream().limit(n);
  }

  private Stream<Basket.BasketPosition> getcheapestProducts(int n) {
    this.list.sort(Comparator.comparingDouble((BasketPosition p) -> -p.getFullPrice(false)).reversed().thenComparing(p -> p.getName()));
    ArrayList<BasketPosition> newList = this.list;
    sortBasket();
    return newList.stream().limit(n);
  }

  private boolean isFreeProduct() {
    return (free_product <= getBasketPrice());
  }

  private String getFreeProduct(ArrayList<Product> shop) {
    for (Product p : shop) {
      if (p.getCode() == "c1") {
        return p.getName();
      }
    }
    return shop.get(0).getName();
  }

  private Double procentageProductDiscount() {
    for (BasketPosition p : this.list) {
      if (p.getCode() == procentageDiscount.code) {
        return p.getAmount()*procentageDiscount.procentage/100.0;
      }
    }
    return 0.0;
  }

  // Funkcje

  public void addToBasket(Product product) {
    for (BasketPosition p : this.list) {
      if (p.product == product) {
        p.amount++;
        return;
      }
    }
    this.list.add(new BasketPosition(product));
    sortBasket();
  }

  public double getPositionPrice(String code) {
    for (BasketPosition p : this.list) {
      if (p.product.getCode() == code) {
        return p.getFullPrice(isDiscount());
      }
    }
    return 0;
  }

  public ArrayList<String> SeeMyBasket() {
    ArrayList<String> result = new ArrayList<String>();
    for (BasketPosition p : this.list) {
      result.add(p.getName()+" in the amount of "+p.getAmount()+" price: "+p.getFullPrice(isDiscount()));
    }
    return result;
  }

  public Double getBasketPrice() {
    Double result = 0.0;
    for (BasketPosition p : this.list) {
      result += p.getFullPrice(isDiscount());
    }
    return Double.valueOf(String.format("%.2f", result));
  }

  private void sortBasket() {
    this.list.sort(Comparator.comparingDouble((BasketPosition p) -> -p.getFullPrice(false)).reversed().thenComparing(p -> p.getName()));
  }

  public void Buy(ArrayList<Product> shop) {
    System.out.println("Do zapłaty: "+getBasketPrice());
    System.out.println("Promocje:");
    if (are3Products()) {
      System.out.println("Darmowy najtańszy produkt do trzech produktów: " + getCheapestProduct(shop));
    }

    if (are3Products()) {
      System.out.println("Darmowy najtańszy produkt do trzech produktów: " + getCheapestProduct(shop));
    }

    if (isFreeProduct()) {
      System.out.println("Darmowy produkt za określoną kwotę: " + getFreeProduct(shop));
    }

    System.out.println("Zwolnienie z płatności spowodowane obniżkami cen produktów: " + procentageProductDiscount());


    



    System.out.println(getCheapestProduct(shop));
  }
}
