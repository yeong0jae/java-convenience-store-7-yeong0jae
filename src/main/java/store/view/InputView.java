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
}
