package store.domain.order;

import java.util.List;
import store.domain.stock.Stock;

public class Order {
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems, Stock stock) {
        hasEnoughQuantity(orderItems, stock);
        this.orderItems = orderItems;
    }

    public void hasEnoughQuantity(List<OrderItem> orderItems, Stock stock) {
        orderItems.forEach(orderItem -> stock.hasEnoughQuantity(
                orderItem.getName(), orderItem.getCount()
        ));
    }

    public List<String> getOrderItemNames() {
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


}
