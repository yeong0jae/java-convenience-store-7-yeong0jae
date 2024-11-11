package store.domain.promotion;

import java.util.List;

public class PromotionCatalog {
    private final List<Promotion> promotions;
    private final Today today;

    public PromotionCatalog(List<Promotion> promotions, Today today) {
        this.promotions = promotions;
        this.today = today;
    }

    public PromotionType findPromotionTypeByName(String name) {
        return findByName(name).getPromotionType();
    }

    public boolean isPromotionActive(String promotionName) {
        return findByName(promotionName).isPromotionActive(today.getToday());
    }

    private Promotion findByName(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(promotionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 프로모션이 없습니다: " + promotionName));
    }
}
