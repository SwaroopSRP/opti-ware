package services;

import utils.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class InsightsGenerator {

    public static void generateInsights(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products in inventory to analyze.");
            return;
        }

        System.out.println("\n--- Inventory Insights ---");


        double totalValue = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
        System.out.println("Total Inventory Value: $" + totalValue);


        Product mostExpensive = products.stream().max(Comparator.comparing(Product::getPrice)).orElse(null);
        Product cheapest = products.stream().min(Comparator.comparing(Product::getPrice)).orElse(null);

        if (mostExpensive != null) {
            System.out.println("Most Expensive Product: " + mostExpensive.getName() + " ($" + mostExpensive.getPrice() + ")");
        }
        if (cheapest != null) {
            System.out.println("Cheapest Product: " + cheapest.getName() + " ($" + cheapest.getPrice() + ")");
        }

        System.out.println("\nTop Selling Products:");
        products.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getUnitsSold(), p1.getUnitsSold()))
                .limit(3) // Show top 3
                .forEach(p -> System.out.println(p.getName() + " - " + p.getUnitsSold() + " units sold"));

        Map<String, Integer> categoryCounts = new HashMap<>();
        for (Product p : products) {
            categoryCounts.put(p.getCategory(), categoryCounts.getOrDefault(p.getCategory(), 0) + p.getQuantity());
        }

        System.out.println("\nCategory-wise Inventory Distribution:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " items");
        }

        System.out.println("\nLow Stock Products (Below 10 units):");
        for (Product p : products) {
            if (p.getQuantity() < 10) {
                System.out.println(p.getName() + " - " + p.getQuantity() + " units");
            }
        }
    }
}
