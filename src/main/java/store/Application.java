package store;

import java.util.List;
import store.controller.ConvenienceStore;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionCatalog;
import store.domain.stock.Stock;
import store.external.TodayImpl;
import store.file.ProductsInput;
import store.file.PromotionsInput;
import store.view.InputView;
import store.view.OutputView;
import store.view.parser.InputParser;

public class Application {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        InputView inputView = new InputView(inputParser);
        OutputView outputView = new OutputView();

        List<Promotion> promotions = PromotionsInput.readPromotions();
        PromotionCatalog promotionCatalog = new PromotionCatalog(promotions, new TodayImpl());
        Stock stock = new Stock(ProductsInput.readProducts());

        ConvenienceStore convenienceStore = new ConvenienceStore(inputView, outputView, stock, promotionCatalog);
        convenienceStore.open();
    }
}
