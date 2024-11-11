package store.controller;

import java.util.List;
import java.util.function.Supplier;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionCatalog;
import store.domain.promotion.PromotionType;
import store.domain.promotion.Today;
import store.domain.receipt.Receipt;
import store.domain.stock.Stock;
import store.file.PromotionsInput;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStore {
    private final InputView inputView;
    private final OutputView outputView;
    private Stock stock;
    private Today today;

    public ConvenienceStore(InputView inputView, OutputView outputView, Stock stock, Today today) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stock = stock;
        this.today = today;
    }

    public void open() {
        outputView.printStock(stock.getProducts());
        PromotionCatalog promotionCatalog = preparePromotion();

        Order order = retryUntilValid(() -> receiveOrder(stock));

        Receipt receipt = pay(order, stock, promotionCatalog);
        applyMembership(receipt);
        outputView.printReceipt(receipt);

        shoppingContinue();
    }

    private Receipt pay(Order order, Stock stock, PromotionCatalog promotionCatalog) {
        Receipt receipt = new Receipt();
        order.getOrderItems().forEach(orderItem -> {
            String name = orderItem.getName();
            int count = orderItem.getCount();
            int price = stock.findPriceByName(name);

            receipt.addPurchaseHistory(name, count, price);

            // 프로모션이 없으면
            if (!stock.hasPromotion(name)) {
                receipt.addMembershipDiscount(count * price);
                stock.decreaseNormalQuantity(name, count);
                return;
            }
            int promotionQuantity = stock.findQuantityOfPromotionByName(name);
            String promotionName = stock.findPromotionNameByName(name);

            // 프로모션 기간이 아니거나, 프로모션 상품이 0개인 경우
            if (!promotionCatalog.isPromotionActive(promotionName) || promotionQuantity == 0) {
                receipt.addMembershipDiscount(count * price);
                stock.decreaseNormalQuantity(name, count);
                return;
            }

            PromotionType promotionType = promotionCatalog.findPromotionTypeByName(promotionName);
            int buy = promotionType.buy;
            int get = promotionType.get;

            // 모든 상품에 프로모션 적용이 가능하면
            if (count <= promotionQuantity) {
                int restCount = count % (buy + get);
                if (restCount == buy) {
                    if (inputView.readAdditionalPromotion(name, get)) {
                        count = orderItem.addCount(get);
                    }
                }
                int givenProductCount = count / (buy + get);

                receipt.updatePurchaseHistoryCount(name, count);
                receipt.addGivenProduct(name, givenProductCount);
                receipt.addPromotionDiscount(givenProductCount * price);
                receipt.addMembershipDiscount(restCount * price);

                stock.decreasePromotionQuantity(name, givenProductCount * (buy + get));
                if (givenProductCount == 0) {
                    stock.decreaseNormalQuantity(name, restCount);
                }
                return;
            }

            // 일부 상품에 프로모션 적용이 가능하면
            int givenProductCount = promotionQuantity / (buy + get);
            int promotionApplyCount = givenProductCount * (buy + get);
            int membershipApplyCount = count - promotionApplyCount;
            if (!inputView.readNoApplicablePromotion(name, membershipApplyCount)) {
                open();
            }
            if (membershipApplyCount > stock.findQuantityOfNormalByName(name)) {
                stock.decreasePromotionQuantity(name, membershipApplyCount - stock.findQuantityOfNormalByName(name));
            }

            receipt.addGivenProduct(name, givenProductCount);
            receipt.addPromotionDiscount(givenProductCount * price);
            receipt.addMembershipDiscount(membershipApplyCount * price);

            stock.decreasePromotionQuantity(name, promotionApplyCount);
            stock.decreaseNormalQuantity(name, count - promotionApplyCount);
        });
        return receipt;
    }

    private void applyMembership(Receipt receipt) {
        if (!inputView.readMembershipDiscountAgree()) {
            receipt.disagreeMembershipDiscount();
        }
    }

    private Order receiveOrder(Stock stock) {
        List<OrderItem> orderItems = inputView.readOrderItems().stream().map(
                rawOrderItem -> new OrderItem(rawOrderItem.getFirst(), rawOrderItem.get(1))
        ).toList();
        return new Order(orderItems, stock);
    }

    private PromotionCatalog preparePromotion() {
        List<Promotion> promotions = PromotionsInput.readPromotions();
        return new PromotionCatalog(promotions, today);
    }

    private void shoppingContinue() {
        if (inputView.readPurchaseAnother()) {
            open();
        }
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
