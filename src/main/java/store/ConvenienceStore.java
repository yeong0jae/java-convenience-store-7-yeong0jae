package store;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionCatalog;
import store.domain.promotion.PromotionType;
import store.domain.receipt.Receipt;
import store.domain.stock.Product;
import store.domain.stock.Stock;
import store.file.ProductsInput;
import store.file.PromotionsInput;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStore {
    private final InputView inputView;
    private final OutputView outputView;

    public ConvenienceStore(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void open() {
        Stock stock = prepareStock();
        outputView.printStock(stock.getProducts());

        PromotionCatalog promotionCatalog = preparePromotion();

        Order order = retryUntilValid(() -> receiveOrder(stock));

        Receipt receipt = pay(order, stock, promotionCatalog);
    }

    private Receipt pay(Order order, Stock stock, PromotionCatalog promotionCatalog) {
        Receipt receipt = new Receipt();

        order.getOrderItems().forEach(orderItem -> {
            String name = orderItem.getName();
            int count = orderItem.getCount();
            int promotionQuantity = stock.findQuantityOfPromotionByName(name);
            String promotionName = stock.findPromotionNameByName(name);
            PromotionType promotionType = promotionCatalog.findPromotionTypeByName(promotionName);

            // 프로모션이 없거나, 프로모션 기간이 아니거나, 프로모션 상품이 0개인 경우
            if (!stock.hasPromotion(name) ||
                    !promotionCatalog.isPromotionActive(promotionName, LocalDate.now()) ||
                    promotionQuantity == 0) {
                return;
            }


        });

        return receipt;
    }

    private Order receiveOrder(Stock stock) {
        List<OrderItem> orderItems = inputView.readOrderItems().stream().map(
                rawOrderItem -> new OrderItem(rawOrderItem.getFirst(), rawOrderItem.get(1))
        ).toList();
        return new Order(orderItems, stock);
    }

    private Stock prepareStock() {
        List<Product> products = ProductsInput.readProducts();
        return new Stock(products);
    }

    private PromotionCatalog preparePromotion() {
        List<Promotion> promotions = PromotionsInput.readPromotions();
        return new PromotionCatalog(promotions);
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
