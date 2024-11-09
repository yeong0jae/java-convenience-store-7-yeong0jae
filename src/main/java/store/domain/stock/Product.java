package store.domain.stock;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    protected int getQuantity() {
        return quantity;
    }

    protected int getPrice() {
        return price;
    }
}
