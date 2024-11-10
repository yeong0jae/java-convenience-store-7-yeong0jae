package store.domain.promotion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionCatalogTest {

    @DisplayName("상품이 프로모션 기간인지 확인한다.")
    @Test
    void isPromotionActiveTest() {
        PromotionCatalog promotionCatalog = new PromotionCatalog(List.of(
                new Promotion("탄산2+1", PromotionGroup.fromBuyGet(2, 1),
                        LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31"))
        ));

        boolean isActive = promotionCatalog.isPromotionActive("탄산2+1", LocalDate.now());

        assertThat(isActive).isTrue();
    }
}
