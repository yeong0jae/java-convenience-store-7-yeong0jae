package store.domain.promotion;

public enum PromotionGroup {
    TWO_PLUS_ONE(2, 1),
    ONE_PLUS_ONE(1, 1);

    public final int buy;
    public final int get;

    PromotionGroup(int buy, int get) {
        this.buy = buy;
        this.get = get;
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
