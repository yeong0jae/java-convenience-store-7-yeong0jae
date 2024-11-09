package store.domain;

public class Payment {
    private Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        this.order = order;
        this.stock = stock;
    }

    public int calculateTotalPurchaseAmount() {
        return order.orderItems.stream()
                .mapToInt(this::calculatePurchaseAmount)
                .sum();
    }

    private int calculatePurchaseAmount(OrderItem orderItem) {
        int price = stock.findPriceByName(orderItem.name);
        return orderItem.calculateTotalPrice(price);
    }
}
