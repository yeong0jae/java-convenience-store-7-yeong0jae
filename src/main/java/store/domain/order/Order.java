package store.domain.order;

import java.util.List;
import store.domain.stock.Stock;
import store.util.ErrorMessage;

public class Order {
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems, Stock stock) {
        validateDuplicate(orderItems);
        validateExists(orderItems, stock);
        validateEnoughQuantity(orderItems, stock);
        this.orderItems = orderItems;
    }

    private void validateDuplicate(List<OrderItem> orderItems) {
        List<String> names = orderItems.stream()
                .map(OrderItem::getName)
                .toList();
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.PREFIX + "중복된 상품 이름을 입력하셨습니다.");
        }
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
