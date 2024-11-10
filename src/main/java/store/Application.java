package store;

import store.domain.stock.Stock;
import store.file.ProductsInput;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Stock stock = new Stock(ProductsInput.readProducts());

        ConvenienceStore convenienceStore =
                new ConvenienceStore(inputView, outputView, stock);
        convenienceStore.open();
    }
}
