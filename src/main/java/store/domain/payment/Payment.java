package store.domain.payment;

import java.util.List;
import store.domain.order.Order;
import store.domain.stock.Stock;

public class Payment {
    private Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        validateExistStock(order, stock);
        this.order = order;
        this.stock = stock;
    }

    private void validateExistStock(Order order, Stock stock) {
        List<String> orderItemNames = order.getOrderItemNames();
        stock.existsByNames(orderItemNames);
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
