package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
    private final String promotionName;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String promotionName, int buy, int get, String startDate, String endDate) {
        this.promotionName = promotionName;
        this.buy = buy;
        this.get = get;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    public boolean isPromotionActive(LocalDate date) {
        return date.isAfter(startDate) && date.isBefore(endDate);
    }
}
