package store.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("상품은 이름, 가격, 재고, 프로모션을 가진다.")
    @Test
    void productTest() {
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";

        Product product = new Product(name, price, quantity, promotion);

        assertAll("product",
                () -> assertEquals("콜라", product.name),
                () -> assertEquals(1000, product.price),
                () -> assertEquals(10, product.quantity),
                () -> assertEquals("탄산2+1", product.promotion)
        );
    }
}
