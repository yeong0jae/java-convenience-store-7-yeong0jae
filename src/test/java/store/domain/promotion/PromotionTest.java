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
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        Promotion promotion = new Promotion(promotionName, buy, get, startDate, endDate);

        boolean isPromotionActive = promotion.isPromotionActive(LocalDate.now());

        assertThat(isPromotionActive).isTrue();
    }
}
