package store.domain.payment;

import store.domain.order.Order;
import store.domain.stock.Stock;

public class Payment {
    private final Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        this.order = order;
        this.stock = stock;
    }

    public int calculateTotalPurchaseAmount() {
        return order.getOrderItemNames().stream()
                .mapToInt(this::calculateItemTotalPrice)
                .sum();
    }

    private int calculateItemTotalPrice(String name) {
        int price = stock.findPriceByName(name);
        int count = order.findCountByName(name);
        return price * count;
    }
}
