package store.domain.stock;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private String promotionName;

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public boolean promotionIsNotNull() {
        return promotionName != null;
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

    protected String getPromotionName() {
        return promotionName;
    }
}
