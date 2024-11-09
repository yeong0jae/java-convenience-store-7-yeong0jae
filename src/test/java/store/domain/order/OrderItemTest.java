package store.domain.order;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.util.ErrorMessage;

class OrderItemTest {

    @DisplayName("상품 수량이 0 이하이면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void validatePositiveCountTest(int count) {
        String name = "콜라";

        assertThatIllegalArgumentException().isThrownBy(
                () -> new OrderItem(name, count)
        ).withMessage(ErrorMessage.PREFIX + "1개 이상의 상품만 구매할 수 있습니다.");
    }
}
