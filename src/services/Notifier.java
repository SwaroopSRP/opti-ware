package services;

import utils.Product;
import java.util.ArrayList;
import java.util.List;

public class Notifier {
    private static final int DEFAULT_THRESHOLD = 10;  // Default low stock threshold

    public static List<Product> checkQuantityLevels(ArrayList<Product> products) {
        List<Product> lowStockProducts = new ArrayList<>();

        for (Product p : products) {
            if (p.getQuantity() < DEFAULT_THRESHOLD) {
                System.out.println("⚠️ ALERT: Low stock for " + p.getName() + " (ID: " + p.getId() + "), Quantity: " + p.getQuantity());
                lowStockProducts.add(p);
            }
        }
        return lowStockProducts;
    }
}