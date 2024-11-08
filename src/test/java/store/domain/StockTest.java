package store.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null)
        );
    }

    @DisplayName("상품 목록을 가진다.")
    @Test
    void stockTest() {
        Stock stock = new Stock(products);

        assertThat(stock.products).isEqualTo(products);
    }

    @DisplayName("구매자가 가져온 상품명으로 상품이 존재하는지 확인한다.")
    @Test
    void hasProductTest() {
        Stock stock = new Stock(products);

        boolean hasProduct = stock.hasProduct("콜라");

        assertThat(hasProduct).isTrue();
    }

    @DisplayName("구매자가 가져온 상품이 존재하지 않으면 예외 처리한다.")
    @Test
    void hasProductExceptionTest() {
        Stock stock = new Stock(products);

        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.hasProduct("사이다")
        ).withMessage("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }
}
