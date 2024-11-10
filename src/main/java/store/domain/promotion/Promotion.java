package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private final String promotionName;
    private final PromotionGroup promotionGroup;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String promotionName, PromotionGroup promotionGroup, LocalDate startDate, LocalDate endDate) {
        this.promotionName = promotionName;
        this.promotionGroup = promotionGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isPromotionActive(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }
}
