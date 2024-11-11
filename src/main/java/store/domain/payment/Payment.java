package store.domain.payment;

import store.domain.order.Order;
import store.domain.promotion.PromotionCatalog;
import store.domain.stock.Stock;

public class Payment {
    private Order order;
    private Stock stock;
    private final PromotionCatalog promotionCatalog;

    public Payment(Order order, Stock stock, PromotionCatalog promotionCatalog) {
        this.order = order;
        this.stock = stock;
        this.promotionCatalog = promotionCatalog;
    }

}
