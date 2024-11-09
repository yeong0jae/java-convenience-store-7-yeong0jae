package store;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.payment.Payment;
import store.domain.stock.Product;
import store.domain.stock.Stock;

public class ConvenienceStoreTest {

    @DisplayName("프로그램 전체 테스트를 진행한다.")
    @Test
    void convenienceStoreTest() {
        // TODO 파일 입력
        Stock stock = new Stock(List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("탄산수", 1500, 5, null)
        ));

        // TODO 콘솔 입력
        Order order = new Order(List.of(
                new OrderItem("콜라", 10),
                new OrderItem("탄산수", 2)),
                stock
        );

        Payment payment = new Payment(order, stock);

        int pay = payment.calculateTotalPurchaseAmount();
    }
}

