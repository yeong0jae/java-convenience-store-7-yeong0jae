package store.domain.promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final PromotionType promotionType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, PromotionType promotionType, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.promotionType = promotionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected boolean isPromotionActive(LocalDateTime now) {
        return startDate.isBefore(now.toLocalDate()) && endDate.isAfter(now.toLocalDate());
    }

    public String getName() {
        return name;
    }

    protected PromotionType getPromotionType() {
        return promotionType;
    }
}
