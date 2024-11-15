package store.domain.promotion;

import store.util.ErrorMessage;

public enum PromotionType {
    TWO_PLUS_ONE(2, 1),
    ONE_PLUS_ONE(1, 1);

    public final int buy;
    public final int get;

    PromotionType(int buy, int get) {
        this.buy = buy;
        this.get = get;
    }

    public static PromotionType fromBuyGet(int buy, int get) {
        if (buy == 2 && get == 1) {
            return TWO_PLUS_ONE;
        }
        if (buy == 1 && get == 1) {
            return ONE_PLUS_ONE;
        }
        throw new IllegalArgumentException(ErrorMessage.PREFIX + "해당하는 buy/get 프로모션은 없습니다.");
    }
}
