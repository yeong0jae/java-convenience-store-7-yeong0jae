package store.domain.promotion;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.util.ErrorMessage;

class PromotionTypeTest {

    @DisplayName("buy, get으로 프로모션 타입을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "2,1,TWO_PLUS_ONE",
            "1,1,ONE_PLUS_ONE"
    })
    void fromBuyGetTest(int buy, int get, String expected) {
        PromotionType promotionType = PromotionType.fromBuyGet(buy, get);

        assertThat(promotionType).isEqualTo(PromotionType.valueOf(expected));
    }

    @DisplayName("해당없는 buy, get은 예외 처리한다.")
    @ParameterizedTest
    @CsvSource({
            "3,2",
            "0,0",
            "2,0",
            "1,0",
    })
    void fromBuyGetExceptionTest(int buy, int get) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> PromotionType.fromBuyGet(buy, get)
        ).withMessage(ErrorMessage.PREFIX + "해당하는 buy/get 프로모션은 없습니다.");
    }
}
