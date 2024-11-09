package store.domain;

public class Payment {
    private final Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        this.order = order;
        this.stock = stock;
    }

    public int calculateTotalPurchaseAmount() {
        return order.getOrderItems().stream()
                .mapToInt(this::calculateOrderItemPrice)
                .sum();
    }

    private int calculateOrderItemPrice(OrderItem orderItem) {
        int price = stock.findPriceByName(orderItem.getName());
        return orderItem.calculateTotalPrice(price);
    }
}
