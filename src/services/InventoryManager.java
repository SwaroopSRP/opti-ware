package services;
import utils.Product;
import utils.CSVHandler;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Product> inventory;
    private String filePath;

    public InventoryManager(String filePath) {
        this.filePath = filePath;
        this.inventory = loadInventory();
    }

    private ArrayList<Product> loadInventory() {
        ArrayList<Product> products = new ArrayList<>();
        for (String line : CSVHandler.read(filePath)) {
            Product product = Product.fromCSV(line);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }

    public void addProduct(Product product) {
        inventory.add(product);
        saveInventory();
    }

    public void updateQuantity(String productId, int newQuantity) {
        for (Product p : inventory) {
            if (p.getId().equals(productId)) {
                p.setQuantity(newQuantity);
                saveInventory();
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void deleteProduct(String productId) {
        inventory.removeIf(p -> p.getId().equals(productId));
        saveInventory();
    }

    public void sellProduct(String productId, int quantitySold) {
        for (Product p : inventory) {
            if (p.getId().equals(productId)) {
                if (p.getQuantity() >= quantitySold) {
                    p.setQuantity(p.getQuantity() - quantitySold);
                    p.addSale(quantitySold);  // Track sales
                    saveInventory();
                    System.out.println("Sale recorded: " + quantitySold + " units of " + p.getName());
                } else {
                    System.out.println("Not enough stock available!");
                }
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public ArrayList<Product> getInventory() { return inventory; }

    private void saveInventory() {
        ArrayList<String> lines = new ArrayList<>();
        for (Product p : inventory) lines.add(p.toCSV());
        CSVHandler.write(filePath, lines);
    }
}
