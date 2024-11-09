package store.domain;

public class Payment {

    public int calculateTotalPurchaseAmount(Order order, Stock stock) {
        return order.orderItems.stream()
                .mapToInt(orderItem -> {
                    int price = stock.findPriceByName(orderItem.name);
                    return orderItem.quantity * price;
                })
                .sum();
    }
}
