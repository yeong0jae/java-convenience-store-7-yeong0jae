package store.domain.stock;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.util.ErrorMessage;

class StockTest {
    private Stock stock;

    @BeforeEach
    void setUp() {
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null),
                new Product("탄산수", 1200, 5, "탄산2+1"),
                new Product("환타", 1500, 5, null)
        );
        stock = new Stock(products);
    }

    @DisplayName("주문한 상품이 존재하지 않으면 예외 처리한다.")
    @Test
    void existsByNameExceptionTest() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.existsByName("사이다")
        ).withMessage(ErrorMessage.PREFIX + "존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 상품의 수량이 재고 수량을 초과하면 예외 처리한다.")
    @ParameterizedTest
    @CsvSource({"콜라,21", "탄산수,6"})
    void hasEnoughQuantityExceptionTest(String name, int quantity) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> stock.hasEnoughQuantity(name, quantity)
        ).withMessage(ErrorMessage.PREFIX + "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 상품의 가격을 확인한다.")
    @Test
    void findPriceByNameTest() {
        int price = stock.findPriceByName("콜라");

        assertThat(price).isEqualTo(1000);
    }

    @DisplayName("주문 상품이 프로모션이 있는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "콜라,true",
            "탄산수,true",
            "환타,false"
    })
    void hasPromotionTest(String name, boolean expected) {
        boolean hasPromotion = stock.hasPromotion(name);

        assertThat(hasPromotion).isEqualTo(expected);
    }

    @DisplayName("주문 상품의 프로모션 명을 확인한다.")
    @Test
    void findPromotionNameByNameTest() {
        String promotionName = stock.findPromotionNameByName("콜라");

        assertThat(promotionName).isEqualTo("탄산2+1");
    }

    @DisplayName("주문 상품의 프로모션 상품 개수를 조회한다.")
    @Test
    void findQuantityOfPromotionByNameTest() {
        int quantityOfPromotion = stock.findQuantityOfPromotionByName("콜라");

        assertThat(quantityOfPromotion).isEqualTo(10);
    }
}
