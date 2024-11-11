package store.controller;

import java.util.List;
import java.util.function.Supplier;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.promotion.PromotionCatalog;
import store.domain.promotion.PromotionType;
import store.domain.receipt.Receipt;
import store.domain.stock.Stock;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceStore {
    private final InputView inputView;
    private final OutputView outputView;
    private Stock stock;
    private final PromotionCatalog promotionCatalog;

    public ConvenienceStore(InputView inputView, OutputView outputView, Stock stock,
                            PromotionCatalog promotionCatalog) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stock = stock;
        this.promotionCatalog = promotionCatalog;
    }

    public void open() {
        outputView.printStock(stock.getProducts());

        Order order = retryUntilValid(() -> receiveOrder(stock));

        Receipt receipt = pay(order);
        applyMembershipDiscount(receipt);
        outputView.printReceipt(receipt);

        shoppingContinue();
    }

    private Receipt pay(Order order) {
        Receipt receipt = new Receipt();

        order.getOrderItems().forEach(orderItem -> {
            if (!payWithoutPromotion(orderItem, receipt)) {
                payWithPromotion(orderItem, receipt);
            }
        });
        return receipt;
    }

    private boolean payWithoutPromotion(OrderItem orderItem, Receipt receipt) {
        String name = orderItem.getName();
        int count = orderItem.getCount();
        int price = stock.findPriceByName(name);

        // 프로모션이 없으면 멤버십 할인만 적용
        if (!stock.hasPromotion(name)) {
            receipt.addPurchaseHistory(name, count, price);
            receipt.addMembershipDiscount(count * price);
            stock.decreaseNormalQuantity(name, count);
            return true;
        }

        int promotionQuantity = stock.findQuantityOfPromotionByName(name);
        String promotionName = stock.findPromotionNameByName(name);

        // 프로모션 기간이 아니거나, 프로모션 상품이 0개인 경우 멤버십 할인만 적용
        if (!promotionCatalog.isPromotionActive(promotionName) || promotionQuantity == 0) {
            receipt.addPurchaseHistory(name, count, price);
            receipt.addMembershipDiscount(count * price);
            stock.decreaseNormalQuantity(name, count);
            return true;
        }
        return false;
    }

    private void payWithPromotion(OrderItem orderItem, Receipt receipt) {
        String name = orderItem.getName();
        int count = orderItem.getCount();
        int price = stock.findPriceByName(name);

        receipt.addPurchaseHistory(name, count, price);

        int promotionQuantity = stock.findQuantityOfPromotionByName(name);
        String promotionName = stock.findPromotionNameByName(name);

        PromotionType promotionType = promotionCatalog.findPromotionTypeByName(promotionName);
        int buy = promotionType.buy;
        int get = promotionType.get;

        // 모든 상품에 프로모션 적용이 가능한 경우
        if (count <= promotionQuantity) {
            int restCount = count % (buy + get);
            if (restCount == buy) {
                if (inputView.readAdditionalPromotion(name, get)) {
                    count = orderItem.addCount(get);
                }
            }
            int givenProductCount = count / (buy + get);

            receipt.updatePurchaseHistoryCount(name, count);

            receipt.applyPromotion(name, givenProductCount, givenProductCount * price);
            receipt.addMembershipDiscount(restCount * price);

            stock.decreasePromotionQuantity(name, givenProductCount * (buy + get));
            if (givenProductCount == 0) {
                stock.decreaseNormalQuantity(name, restCount);
            }
            return;
        }

        // 일부 상품에 프로모션 적용이 가능한 경우
        int givenProductCount = promotionQuantity / (buy + get);
        int promotionApplyCount = givenProductCount * (buy + get);
        int membershipApplyCount = count - promotionApplyCount;
        if (!inputView.readNoApplicablePromotion(name, membershipApplyCount)) {
            open();
        }
        if (membershipApplyCount > stock.findQuantityOfNormalByName(name)) {
            stock.decreasePromotionQuantity(name, membershipApplyCount - stock.findQuantityOfNormalByName(name));
        }

        receipt.applyPromotion(name, givenProductCount, givenProductCount * price);
        receipt.addMembershipDiscount(membershipApplyCount * price);

        stock.decreasePromotionQuantity(name, promotionApplyCount);
        stock.decreaseNormalQuantity(name, count - promotionApplyCount);
    }

    private void applyMembershipDiscount(Receipt receipt) {
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
