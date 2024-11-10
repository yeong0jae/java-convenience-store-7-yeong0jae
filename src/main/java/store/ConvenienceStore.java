package store;

import java.util.List;
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
        List<Product> products = ProductsInput.readProducts();
        Stock stock = new Stock(products);

        List<Promotion> promotions = PromotionsInput.readPromotions();
        PromotionCatalog promotionCatalog = new PromotionCatalog(promotions);

        
    }
}
