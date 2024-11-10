package store.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionGroup;

public class PromotionsInput {
    private static final String FILE_PATH = "src/main/resources/promotions.md";

    public static List<Promotion> readPromotions() {
        List<String> lines = readLines();
        List<Promotion> promotions = new ArrayList<>();

        for (String line : lines) {
            Promotion promotion = parsePromotion(line);
            promotions.add(promotion);
        }
        return promotions;
    }

    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static Promotion parsePromotion(String line) {
        List<String> rawPromotion = Arrays.stream(line.split(",")).toList();
        String name = rawPromotion.get(0);
        int buy = Integer.parseInt(rawPromotion.get(1));
        int get = Integer.parseInt(rawPromotion.get(2));
        PromotionGroup promotionGroup = PromotionGroup.fromBuyGet(buy, get);
        LocalDate startDate = LocalDate.parse(rawPromotion.get(3));
        LocalDate endDate = LocalDate.parse(rawPromotion.get(4));

        return new Promotion(name, promotionGroup, startDate, endDate);
    }
}
