import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Product> shop = new ArrayList<Product>();
    Product a1 = new Product("a1", "white milk", 4.99);
    Product a2 = new Product("a2", "farmer's bread", 1.99);
    Product a3 = new Product("a3", "eggs type 0", 10.99);
    Product b1 = new Product("b1", "vacuum sigma1", 200.99);
    Product c1 = new Product("c1", "Lego flowers", 49.99);
    Product c2 = new Product("c2", "Brazilia flag", 24.99);

    shop.add(a1);
    shop.add(a2);
    shop.add(a3);
    shop.add(b1);
    shop.add(c1);
    shop.add(c2);

    Basket basket = new Basket();

    basket.addToBasket(a1);
    basket.addToBasket(b1);
    basket.addToBasket(a1);
    basket.addToBasket(c2);
    basket.addToBasket(a2);
    basket.addToBasket(a3);

    System.out.println(basket.SeeMyBasket());
    // System.out.println(basket.getBasketPrice());

    basket.Buy(shop);
  }
}