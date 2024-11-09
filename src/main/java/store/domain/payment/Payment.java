package store.domain.payment;

import java.util.List;
import store.domain.order.Order;
import store.domain.stock.Stock;

public class Payment {
    private Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        existsProduct(order, stock);
        this.order = order;
        this.stock = stock;
    }

    private void existsProduct(Order order, Stock stock) {
        List<String> orderItemNames = order.getOrderItemNames();
        stock.existsByNames(orderItemNames);
    }

    public int calculateTotalPurchaseAmount() {
        return order.getOrderItemNames().stream()
                .mapToInt(this::calculateItemTotalPrice)
                .sum();
    }

    private int calculateItemTotalPrice(String name) {
        int count = order.findCountByName(name);
        int price = stock.findPriceByName(name);
        return price * count;
    }
}
