package store.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @DisplayName("주문 상품은 상품명, 수량을 가진다.")
    @Test
    void orderItemTest() {
        String name = "콜라";
        int quantity = 10;

        OrderItem orderItem = new OrderItem(name, quantity);

        assertThat(orderItem.name).isEqualTo(name);
        assertThat(orderItem.quantity).isEqualTo(quantity);
    }
}
