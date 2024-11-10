package store.domain.receipt;

public class GivenProduct {
    private String name;
    private int count;

    public GivenProduct(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
