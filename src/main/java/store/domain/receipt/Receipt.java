package store.domain.receipt;

public class Receipt {
    private final int totalPurchaseAmount;
    private final int promotionDiscount;
    private final int membershipDiscount;
    private final int finalAmount;

    public Receipt(int totalPurchaseAmount, int promotionDiscount, int membershipDiscount, int finalAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.finalAmount = finalAmount;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getFinalAmount() {
        return finalAmount;
    }
}
