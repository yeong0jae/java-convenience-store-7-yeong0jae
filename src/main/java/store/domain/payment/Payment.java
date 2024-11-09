package store.domain.payment;

import java.util.List;
import store.domain.order.Order;
import store.domain.stock.Stock;

public class Payment {
    private Order order;
    private Stock stock;

    public Payment(Order order, Stock stock) {
        this.order = order;
        this.stock = stock;
    }

    public void promotionDiscount() {
        List<String> orderItemNames = order.findOrderItemNames();
        orderItemNames.forEach(orderItemName -> {
            // 프로모션이 있는가?
            boolean hasPromotion = stock.hasPromotion(orderItemName);
            if (!hasPromotion) {
                return;
            }
            // 프로모션 기간인가?
//            boolean isPromotionActive = stock.isPromotionActive(orderItemName);

            // 프로모션 ...
        });
    }

    public int calculateTotalPurchaseAmount() {
        return order.findOrderItemNames().stream()
                .mapToInt(this::calculateItemTotalPrice)
                .sum();
    }

    private int calculateItemTotalPrice(String name) {
        int count = order.findCountByName(name);
        int price = stock.findPriceByName(name);
        return price * count;
    }
}
