package store.domain.promotion;

import java.time.LocalDate;
import java.util.List;

public class PromotionCatalog {
    private final List<Promotion> promotions;

    public PromotionCatalog(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public PromotionType findPromotionTypeByName(String name) {
        return findByName(name).getPromotionType();
    }

    public boolean isPromotionActive(String promotionName, LocalDate currentDate) {
        return findByName(promotionName).isPromotionActive(currentDate);
    }

    private Promotion findByName(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(promotionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 프로모션이 없습니다: " + promotionName));
    }
}
