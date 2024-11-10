package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final PromotionGroup promotionGroup;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, PromotionGroup promotionGroup, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.promotionGroup = promotionGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected boolean isPromotionActive(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }

    public String getName() {
        return name;
    }
}
