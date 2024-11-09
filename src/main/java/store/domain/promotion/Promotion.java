package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private final String promotionName;
    private final PromotionGroup promotionGroup;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String promotionName, PromotionGroup promotionGroup, String startDate, String endDate) {
        this.promotionName = promotionName;
        this.promotionGroup = promotionGroup;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public boolean isPromotionActive(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }
}
