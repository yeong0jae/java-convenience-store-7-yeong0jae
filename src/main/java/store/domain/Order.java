package store.domain;

import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<String> getOrderItemNames() {
        return orderItems.stream().map(
                OrderItem::getName
        ).toList();
    }

    public int findQuantityByName(String name) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.matchesName(name))
                .map(OrderItem::getQuantity)
                .findFirst()
                .orElseThrow();
    }
}
