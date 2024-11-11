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

    public int getAmount() {
        return count * price;
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

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    public void updateCount(int count) {
        this.count = count;
    }
}
