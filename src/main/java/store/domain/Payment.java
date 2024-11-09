package store.domain;

import java.util.List;

public class Payment {

    public int calculateTotalPurchaseAmount(Order order, Stock stock) {
        List<String> orderNames = order.orderItems.stream().map(
                orderItem -> orderItem.name
        ).toList();
        List<Integer> orderQuantities = order.orderItems.stream().map(
                orderItem -> orderItem.quantity
        ).toList();
        List<Integer> prices = orderNames.stream().map(
                stock::findPriceByName
        ).toList();

        int totalPurchaseAmount = 0;
        int orderSize = order.orderItems.size();
        for (int i = 0; i < orderSize; i++) {
            totalPurchaseAmount += orderQuantities.get(i) * prices.get(i);
        }
        return totalPurchaseAmount;
    }
}
