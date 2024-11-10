package store.domain.order;

import java.util.List;
import store.domain.stock.Stock;

public class Order {
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems, Stock stock) {
        validateExists(orderItems, stock);
        validateEnoughQuantity(orderItems, stock);
        this.orderItems = orderItems;
    }

    public int findCountByName(String name) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.matchesName(name))
                .map(OrderItem::getCount)
                .findFirst()
                .orElseThrow();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    private void validateExists(List<OrderItem> orderItems, Stock stock) {
        orderItems.forEach(orderItem -> stock.existsByName(orderItem.getName()));
    }

    private void validateEnoughQuantity(List<OrderItem> orderItems, Stock stock) {
        orderItems.forEach(orderItem -> stock.hasEnoughQuantity(
                orderItem.getName(), orderItem.getCount()
        ));
    }
}
