package store.domain.payment;

import java.time.LocalDate;
import java.util.List;
import store.domain.order.Order;
import store.domain.promotion.PromotionCatalog;
import store.domain.stock.Stock;

public class Payment {
    private Order order;
    private Stock stock;
    private PromotionCatalog promotionCatalog;

    public Payment(Order order, Stock stock, PromotionCatalog promotionCatalog) {
        this.order = order;
        this.stock = stock;
        this.promotionCatalog = promotionCatalog;
    }

    public void promotionDiscount() {
        List<String> orderItemNames = order.findOrderItemNames();
        orderItemNames.forEach(orderItemName -> {
            boolean hasPromotion = stock.hasPromotion(orderItemName);
            if (!hasPromotion) {
                return;
            }
            String promotionName = stock.findPromotionNameByName(orderItemName);
            boolean isPromotionActive = promotionCatalog.isPromotionActive(promotionName, LocalDate.now());
            if (!isPromotionActive) {
                return;
            }
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
