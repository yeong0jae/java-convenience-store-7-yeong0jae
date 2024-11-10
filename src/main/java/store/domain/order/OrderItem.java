package store.domain.order;

import store.util.ErrorMessage;

public class OrderItem {
    private final String name;
    private int count;

    public OrderItem(String name, String count) {
        int rawCount = Integer.parseInt(count);
        validatePositiveCount(rawCount);
        this.name = name;
        this.count = rawCount;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    private void validatePositiveCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ErrorMessage.PREFIX + "1개 이상의 상품만 구매할 수 있습니다.");
        }
    }

    public void addCount() {
        count += 1;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
