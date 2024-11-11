package store;

import store.controller.ConvenienceStore;
import store.domain.promotion.Today;
import store.domain.stock.Stock;
import store.external.TodayImpl;
import store.file.ProductsInput;
import store.view.InputView;
import store.view.OutputView;
import store.view.parser.InputParser;

public class Application {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        InputView inputView = new InputView(inputParser);

        OutputView outputView = new OutputView();
        Stock stock = new Stock(ProductsInput.readProducts());

        Today today = new TodayImpl();

        ConvenienceStore convenienceStore = new ConvenienceStore(inputView, outputView, stock, today);
        convenienceStore.open();
    }
}
