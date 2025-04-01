import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> shop = new ArrayList<>();
        shop.add(new Product("a1", "white milk", 4.99));
        shop.add(new Product("a2", "farmer's bread", 1.99));
        shop.add(new Product("a3", "eggs type 0", 10.99));
        shop.add(new Product("b1", "vacuum sigma1", 200.99));
        shop.add(new Product("c1", "Lego flowers", 49.99));
        shop.add(new Product("c2", "Brazilia flag", 24.99));

        ArrayList<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion3Products());
        promotions.add(new Promotion5Procent());
        promotions.add(new PromotionFreeProduct());
        promotions.add(new PromotionProductDiscount());

        Basket basket = new Basket(shop);

        for (Promotion p : promotions) {
            basket.addPromotion(p);
        }

        basket.addToBasket(shop.get(0));
        basket.addToBasket(shop.get(3));
        basket.addToBasket(shop.get(0));
        basket.addToBasket(shop.get(5));
        basket.addToBasket(shop.get(1));
        basket.addToBasket(shop.get(2));

        basket.printAllProducts();
    }
}
