package store.domain.order;

public class OrderItem {
    private final String name;
    private int quantity;

    public OrderItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
