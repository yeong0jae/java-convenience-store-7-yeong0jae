package store;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.payment.Payment;
import store.domain.stock.Product;
import store.domain.stock.Stock;
import store.file.ProductsInput;

public class ConvenienceStoreTest {

    @DisplayName("프로그램 전체 테스트를 진행한다.")
    @Test
    void convenienceStoreTest() {
        List<Product> products = ProductsInput.readProducts();
        Stock stock = new Stock(products);

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

