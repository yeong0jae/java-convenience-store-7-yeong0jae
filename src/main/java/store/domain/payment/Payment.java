package store.domain.payment;

import java.time.LocalDate;
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

    public void applyPromotionDiscount() {
        order.findOrderItemNames().forEach(orderItemName -> {
            if (!stock.hasPromotion(orderItemName)) {
                return;
            }

            String promotionName = stock.findPromotionNameByName(orderItemName);
            if (!promotionCatalog.isPromotionActive(promotionName, LocalDate.now())) {
                return;
            }
            
            applyDiscount();
        });
    }

    private void applyDiscount() {
        // 할인 적용 로직 구현
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
