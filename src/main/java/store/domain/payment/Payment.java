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

    public void isPromotionDiscountApplicable() {
        order.findOrderItemNames().forEach(orderItemName -> {
            if (!stock.hasPromotion(orderItemName)) {
                throw new IllegalArgumentException("프로모션 없음");
            }

            String promotionName = stock.findPromotionNameByName(orderItemName);
            if (!promotionCatalog.isPromotionActive(promotionName, LocalDate.now())) {
                throw new IllegalArgumentException("프로모션 기간아님");
            }

            applyDiscount(orderItemName);
        });
    }

    private void applyDiscount(String orderItemName) {
        // 단일 상품에 대한 할인 적용 로직 구현

        // 구매자가 가져온 상품 개수
        int count = order.findCountByName(orderItemName);
        // 프로모션 상품의 개수
        int quantityOfPromotion = stock.findQuantityOfPromotionByName(orderItemName);
        
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
