package store.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StockTest {
    private List<Product> products;
    private Stock stock;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null),
                new Product("탄산수", 1200, 5, "탄산2+1")
        );
        stock = new Stock(products);
    }

    @DisplayName("상품 목록을 가진다.")
    @Test
    void stockTest() {
        assertThat(stock.products).isEqualTo(products);
    }

    @DisplayName("요청한 상품이 존재하는지 확인한다.")
    @Test
    void existsByNameTest() {
        boolean productExists = stock.existsByName("콜라");

        assertThat(productExists).isTrue();
    }

    @DisplayName("요청한 상품이 존재하지 않으면 예외 처리한다.")
    @Test
    void existsByNameExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.existsByName("사이다")
        ).withMessage("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }

    @DisplayName("요청한 상품의 수량이 재고 수량보다 작은지 확인한다.")
    @ParameterizedTest
    @CsvSource(
            {
                    "콜라,19,true",
                    "콜라,21,false"
            }
    )
    void hasEnoughStockTest(String name, int quantity, boolean expected) {
        boolean hasEnoughStock = stock.hasEnoughStock(name, quantity);

        assertThat(hasEnoughStock).isEqualTo(expected);
    }
}
