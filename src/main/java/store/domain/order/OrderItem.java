package store.domain.order;

import store.util.ErrorMessage;

public class OrderItem {
    private final String name;
    private int count;

    public OrderItem(String name, int count) {
        validatePositiveCount(count);
        this.name = name;
        this.count = count;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    private void validatePositiveCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ErrorMessage.PREFIX + "1개 이상의 상품만 구매할 수 있습니다.");
        }
    }

    protected String getName() {
        return name;
    }

    protected int getCount() {
        return count;
    }
}
