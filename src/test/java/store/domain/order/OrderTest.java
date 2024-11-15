package store.domain.order;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.stock.Product;
import store.domain.stock.Stock;
import store.util.ErrorMessage;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", "10"),
                new OrderItem("탄산수", "3")
        );
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null),
                new Product("탄산수", 1200, 5, "탄산2+1")
        );
        order = new Order(orderItems, new Stock(products));
    }

    @DisplayName("주문 수량을 확인한다.")
    @Test
    void findCountByNameTest() {
        int orderQuantity = order.findCountByName("콜라");

        assertThat(orderQuantity).isEqualTo(10);
    }

    @DisplayName("주문 상품이 중복되어 있는 경우 예외를 발생시킨다.")
    @Test
    void validateDuplicateTest() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", "10"),
                new OrderItem("콜라", "3")
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                new Order(orderItems, new Stock(List.of()))
        ).withMessage(ErrorMessage.PREFIX + "중복된 상품 이름을 입력하셨습니다.");
    }
}
