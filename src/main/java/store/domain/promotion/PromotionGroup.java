package store.domain.promotion;

import java.util.List;

public enum PromotionGroup {
    TWO_PLUS_ONE(2, 1, List.of(PromotionType.CARBONATED_TWO_PLUS_ONE)),
    ONE_PLUS_ONE(1, 1, List.of(PromotionType.MD_RECOMMENDED, PromotionType.SHINY_DISCOUNT));

    public final int buy;
    public final int get;
    private final List<PromotionType> promotionTypes;

    PromotionGroup(int buy, int get, List<PromotionType> promotionTypes) {
        this.buy = buy;
        this.get = get;
        this.promotionTypes = promotionTypes;
    }

    public static PromotionGroup fromBuyGet(int buy, int get) {
        if (buy == 2 && get == 1) {
            return TWO_PLUS_ONE;
        }
        if (buy == 1 && get == 1) {
            return ONE_PLUS_ONE;
        }
        return null;
    }
}
