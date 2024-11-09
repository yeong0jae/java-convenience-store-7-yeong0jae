package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private final String promotionName;
    private final PromotionType promotionType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String promotionName, PromotionType promotionType, String startDate, String endDate) {
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public boolean isPromotionActive(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }
}
