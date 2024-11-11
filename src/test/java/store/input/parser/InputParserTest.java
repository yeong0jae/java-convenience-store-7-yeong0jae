package store.input.parser;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.view.parser.InputParser;

class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @DisplayName("주문 입력 실패: 대괄호 검증")
    @ParameterizedTest
    @ValueSource(strings = {"[콜라-1", "콜라-1]", "콜라-1"})
    void validateBracketsTest(String rawOrderItem) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputParser.parseOrderItem(rawOrderItem));
    }

    @DisplayName("주문 입력 실패: 구분자 검증")
    @ParameterizedTest
    @ValueSource(strings = {"[콜라,1]", "[콜라:1]", "[콜라/1]", "[콜라 1]"})
    void validatePartsSizeTest(String rawOrderItem) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputParser.parseOrderItem(rawOrderItem));
    }

    @DisplayName("주문 입력 실패: 상품명 검증")
    @ParameterizedTest
    @ValueSource(strings = {"[-1]", "[ -1]"})
    void validateProductNameTest(String rawOrderItem) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputParser.parseOrderItem(rawOrderItem));
    }
    
}
