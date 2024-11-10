package store.domain.receipt;

public class PurchaseHistory {
    private String name;
    private int count;
    private int price;

    public PurchaseHistory(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
