package store.domain.promotion;

public enum PromotionType {
    CARBONATED_TWO_PLUS_ONE("탄산2+1"),
    MD_RECOMMENDED("MD추천상품"),
    SHINY_DISCOUNT("반짝할인");

    private String name;

    PromotionType(String name) {
        this.name = name;
    }
}
