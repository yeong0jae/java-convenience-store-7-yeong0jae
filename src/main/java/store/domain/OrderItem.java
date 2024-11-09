package store.domain;

public class OrderItem {
    private final String name;
    private int quantity;

    public OrderItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int calculateTotalPrice(int price) {
        return this.quantity * price;
    }

    public String getName() {
        return name;
    }
}
