package store.domain.promotion;

import java.util.List;

public class PromotionCatalog {
    private final List<Promotion> promotions;
    
    public PromotionCatalog(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
