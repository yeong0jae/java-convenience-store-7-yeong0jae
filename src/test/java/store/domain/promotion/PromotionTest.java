package store.domain.promotion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @DisplayName("구매자가 가져온 상품이 프로모션 기간인지 확인한다.")
    @Test
    void isPromotionActiveTest() {
        String promotionName = "탄산2+1";
        int buy = 2;
        int get = 1;
        PromotionGroup promotionGroup = PromotionGroup.fromBuyGet(buy, get);
        LocalDate startDate = LocalDate.parse("2024-01-01");
        LocalDate endDate = LocalDate.parse("2024-12-31");
        Promotion promotion = new Promotion(promotionName, promotionGroup, startDate, endDate);

        boolean isPromotionActive = promotion.isPromotionActive(LocalDate.now());

        assertThat(isPromotionActive).isTrue();
    }
}
