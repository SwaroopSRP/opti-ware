package utils;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int unitsSold;

    public Product(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.unitsSold = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addSale(int quantitySold) {
        this.unitsSold += quantitySold;
    }


    public String toCSV() {
        return id + "," + name + "," + category + "," + price + "," + quantity + "," + unitsSold;
    }

    public static Product fromCSV(String line) {
        String[] parts = line.split(",");


        if (parts.length < 5) {
            System.out.println("Skipping invalid CSV line: " + line);
            return null;
        }

        try {
            return new Product(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing CSV line: " + line + " - " + e.getMessage());
            return null;
        }
    }
}
