package store.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문은 주문 상품 목록을 가진다.")
    @Test
    void orderTest() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", 10),
                new OrderItem("탄산수", 3)
        );

        Order order = new Order(orderItems);

        assertThat(order.getOrderItems()).isEqualTo(orderItems);
    }
}
