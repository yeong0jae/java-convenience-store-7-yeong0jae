package store;

import java.util.List;
import java.util.function.Supplier;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionCatalog;
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
