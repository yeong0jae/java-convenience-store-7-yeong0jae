package store.domain.stock;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public void decreaseQuantity(int count) {
        quantity -= count;
    }

    public boolean promotionIsNotNull() {
        return promotionName != null;
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

    public int getPrice() {
        return price;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
