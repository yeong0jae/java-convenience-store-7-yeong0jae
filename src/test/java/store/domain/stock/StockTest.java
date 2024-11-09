package store.domain.stock;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StockTest {
    private Stock stock;

    @BeforeEach
    void setUp() {
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null),
                new Product("탄산수", 1200, 5, "탄산2+1")
        );
        stock = new Stock(products);
    }

    @DisplayName("주문한 상품이 존재하는지 확인한다.")
    @Test
    void existsByNameTest() {
        boolean productExists = stock.existsByName("콜라");

        assertThat(productExists).isTrue();
    }

    @DisplayName("주문한 상품이 존재하지 않으면 예외 처리한다.")
    @Test
    void existsByNameExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.existsByName("사이다")
        ).withMessage("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 상품의 수량이 재고 수량 이하인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"콜라,19", "탄산수,4"})
    void hasEnoughStockTest(String name, int quantity) {
        boolean hasEnoughStock = stock.hasEnoughStock(name, quantity);

        assertThat(hasEnoughStock).isTrue();
    }

    @DisplayName("주문한 상품의 수량이 재고 수량보다 큰지 확인한다.")
    @ParameterizedTest
    @CsvSource({"콜라,21", "탄산수,6"})
    void hasEnoughStockExceptionTest(String name, int quantity) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.hasEnoughStock(name, quantity)
        ).withMessage("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 상품의 가격을 확인한다.")
    @Test
    void findPriceByNameTest() {
        int price = stock.findPriceByName("콜라");

        assertThat(price).isEqualTo(1000);
    }
}
