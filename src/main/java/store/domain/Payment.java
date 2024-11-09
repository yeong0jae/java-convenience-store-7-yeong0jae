package store.domain;

public class Payment {

    public int calculateTotalPurchaseAmount(Order order, Stock stock) {
        return order.orderItems.stream()
                .mapToInt(item -> calculatePurchaseAmount(item, stock))
                .sum();
    }

    private int calculatePurchaseAmount(OrderItem orderItem, Stock stock) {
        int price = stock.findPriceByName(orderItem.name);
        return orderItem.calculateTotalPrice(price);
    }
}
