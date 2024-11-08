package store.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

    @DisplayName("상품 목록을 가진다.")
    @Test
    void stockTest() {
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null)
        );

        Stock stock = new Stock(products);

        assertThat(stock.products).isEqualTo(products);
    }

    @DisplayName("구매자가 가져온 상품명으로 상품이 존재하는지 확인한다.")
    @Test
    void hasProductTest() {
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null)
        );
        Stock stock = new Stock(products);

        boolean hasProduct = stock.hasProduct("콜라");

        assertThat(hasProduct).isTrue();
    }
}
