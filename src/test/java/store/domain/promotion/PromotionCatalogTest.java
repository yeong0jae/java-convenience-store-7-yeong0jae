package store.domain.promotion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionCatalogTest {
    private PromotionCatalog promotionCatalog;

    @BeforeEach
    void setUp() {
        promotionCatalog = new PromotionCatalog(List.of(
                new Promotion("탄산2+1", PromotionType.fromBuyGet(2, 1),
                        LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31")),
                new Promotion("MD추천상품", PromotionType.fromBuyGet(2, 1),
                        LocalDate.parse("2024-11-01"), LocalDate.parse("2024-11-30"))
        ), () -> LocalDate.of(2024, 2, 1));
    }

    @DisplayName("상품이 프로모션 기간인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "탄산2+1,true",
            "MD추천상품,false"
    })
    void isPromotionActiveTest(String promotionName, boolean expected) {
        boolean isActive = promotionCatalog.isPromotionActive(promotionName);

        assertThat(isActive).isEqualTo(expected);
    }

    @DisplayName("주문 상품의 프로모션 타입을 조회한다.")
    @Test
    void findPromotionTypeByNameTest() {
        PromotionType promotionType = promotionCatalog.findPromotionTypeByName("탄산2+1");

        assertThat(promotionType).isEqualTo(PromotionType.valueOf("TWO_PLUS_ONE"));
    }
}
