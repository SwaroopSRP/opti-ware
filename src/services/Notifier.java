package services;
import utils.Product;
import java.util.ArrayList;

public class Notifier {
    public static void checkQuantityLevels(ArrayList<Product> products) {
        for (Product p : products) {
            if (p.getQuantity() < 10) {
                System.out.println("ALERT!: Low quantity for " + p.getName());
            }
        }
    }
}
