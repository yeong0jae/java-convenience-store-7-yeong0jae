package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    public List<List<String>> readOrderItems() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();

        return Arrays.stream(input.split(","))
                .map(this::parseOrderItem)
                .toList();
    }

    private List<String> parseOrderItem(String rawOrderItem) {
        String replacedInput = rawOrderItem.replace("[", "").replace("]", "");
        List<String> parts = Arrays.asList(replacedInput.split("-"));

        if (parts.size() != 2 || parts.get(0).isEmpty() || !isNumeric(parts.get(1))) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }

        return parts;
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public boolean readAdditionalPromotion(String name, int count) {
        System.out.printf("\n현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n", name, count);
        return isYes();
    }

    public boolean readNoApplicablePromotion(String name, int count) {
        System.out.printf("\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n", name, count);
        return isYes();
    }

    public boolean readMembershipDiscountAgree() {
        System.out.println("\n멤버십 할인을 받으시겠습니까? (Y/N)");
        return isYes();
    }

    public boolean readPurchaseAnother() {
        System.out.println("\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return isYes();
    }

    private boolean isYes() {
        return Console.readLine().equalsIgnoreCase("Y");
    }
}
