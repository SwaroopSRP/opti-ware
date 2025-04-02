package services;

import utils.CSVHandler;
import java.util.*;

public class SalesAnalyzer {
    private static String salesFile = "assets/sales.csv";

    public static void generateInsights() {
        List<String> salesData = CSVHandler.read(salesFile);
        Map<String, Integer> salesCount = new HashMap<>();

        for (String line : salesData) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            String productName = parts[1];
            int sold = Integer.parseInt(parts[2]);

            salesCount.put(productName, salesCount.getOrDefault(productName, 0) + sold);
        }

        List<Map.Entry<String, Integer>> sortedSales = new ArrayList<>(salesCount.entrySet());
        sortedSales.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\n📊 Top-Selling Products:");
        for (int i = 0; i < Math.min(5, sortedSales.size()); i++) {
            System.out.println((i + 1) + ". " + sortedSales.get(i).getKey() + " - " + sortedSales.get(i).getValue() + " units sold");
        }
    }
}