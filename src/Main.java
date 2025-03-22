import utils.Product;
import services.InventoryManager;
import services.Notifier;
//import services.InsightsGenerator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager("assets/inventory.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWarehouse Inventory System");
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Update Quantity");
            System.out.println("4. Delete Product");
            System.out.println("5. Check Quantity Levels");
            System.out.println("6. Generate Insights");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    manager.addProduct(new Product(id, name, category, price, quantity));
                    break;
                case 2:
                    for (Product p : manager.getInventory()) {
                        System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategory() + " | " + p.getPrice() + " | " + p.getQuantity());
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.next();
                    System.out.print("Enter New Quantity: ");
                    int newQuantity = scanner.nextInt();
                    manager.updateQuantity(productId, newQuantity);
                    break;
                case 4:
                    System.out.print("Enter Product ID: ");
                    manager.deleteProduct(scanner.next());
                    break;
                case 5:
                    Notifier.checkQuantityLevels(manager.getInventory());
                    break;
                case 6:
//                    InsightsGenerator.generateInsights(manager.getInventory());
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}
