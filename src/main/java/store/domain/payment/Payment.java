package store.domain.payment;

import java.time.LocalDate;
import java.util.List;
import store.domain.order.Order;
import store.domain.promotion.PromotionCatalog;
import store.domain.promotion.PromotionType;
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

    public List<Integer> calculateTotalGets() {
        return order.findOrderItemNames().stream()
                .map(orderItemName -> {
                    isPromotionApplicable(orderItemName);
                    return calculateTotalGet(orderItemName);
                }).toList();
    }

    // 단일 상품에 대한 증정 상품 개수 구하기 로직
    private int calculateTotalGet(String orderItemName) {

        // 구매자가 가져온 상품 개수
        int count = order.findCountByName(orderItemName);
        // 프로모션 상품의 개수
        int quantityOfPromotion = stock.findQuantityOfPromotionByName(orderItemName);

        // 주문 상품의 프로모션 타입
        String promotionName = stock.findPromotionNameByName(orderItemName);
        PromotionType promotionType = promotionCatalog.findPromotionTypeByName(promotionName);

        // 프로모션 상품 재고가 없는 경우 (증정 상품 0개)
        if (quantityOfPromotion == 0) {
            return 0;
        }

        // 모든 주문에 프로모션 적용 가능한 경우
        if (quantityOfPromotion >= count) {
            // 증정 상품 개수
            return count / (promotionType.buy + promotionType.get);
        }

        // 일부 주문에 프로모션 적용 가능한 경우
        return quantityOfPromotion / (promotionType.buy + promotionType.get);
    }

    private void isPromotionApplicable(String orderItemName) {
        if (!stock.hasPromotion(orderItemName)) {
            return;
        }

        String promotionName = stock.findPromotionNameByName(orderItemName);
        if (!promotionCatalog.isPromotionActive(promotionName, LocalDate.now())) {
            return;
        }
    }
}
