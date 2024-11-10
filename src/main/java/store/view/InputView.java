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
        return Arrays.asList(replacedInput.split("-"));
    }

    public boolean readAdditionalPromotion(String name, int count) {
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n", name, count);
        return isYes();
    }

    public boolean readNoApplicablePromotion(String name, int count) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n", name, count);
        return isYes();
    }

    public boolean readMembershipDiscountAgree() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return isYes();
    }

    private boolean isYes() {
        return Console.readLine().equalsIgnoreCase("Y");
    }
}
