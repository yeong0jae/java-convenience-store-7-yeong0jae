package store.domain.order;

import java.util.List;
import store.domain.stock.Stock;

public class Order {
    private List<OrderItem> orderItems;
    private Stock stock;

    public Order(List<OrderItem> orderItems, Stock stock) {
        validateExists(orderItems, stock);
        validateEnoughQuantity(orderItems, stock);
        this.orderItems = orderItems;
        this.stock = stock;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public List<String> findOrderItemNames() {
        return orderItems.stream()
                .map(OrderItem::getName)
                .toList();
    }

    public int findCountByName(String name) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.matchesName(name))
                .map(OrderItem::getCount)
                .findFirst()
                .orElseThrow();
    }

    public int calculateTotalPurchaseAmount() {
        return orderItems.stream()
                .mapToInt(orderItem -> calculateItemTotalPrice(orderItem.getName()))
                .sum();
    }

    private int calculateItemTotalPrice(String name) {
        int count = findCountByName(name);
        int price = stock.findPriceByName(name);
        return price * count;
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
