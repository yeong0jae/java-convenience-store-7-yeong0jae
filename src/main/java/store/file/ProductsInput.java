package store.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import store.domain.stock.Product;

public class ProductsInput {
    private static final String FILE_PATH = "src/main/resources/products.md";

    public static List<Product> readProducts() {
        List<String> lines = readLines();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            Product product = parseProduct(lines.get(i));
            products.add(product);

            // 현재 상품이 프로모션이 있고, 다음 라인이 다른 상품이면 기본 상품 추가
            if (product.getPromotionName() != null && (i + 1 >= lines.size() || !isSameProductNameAndPrice(
                    lines.get(i + 1), product))) {
                products.add(new Product(product.getName(), product.getPrice(), 0, null));
            }
        }

        return products;
    }

    private static boolean isSameProductNameAndPrice(String line, Product product) {
        List<String> rawProduct = Arrays.stream(line.split(",")).toList();
        String name = rawProduct.get(0);
        int price = Integer.parseInt(rawProduct.get(1));
        return name.equals(product.getName()) && price == product.getPrice();
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

    private static Product parseProduct(String line) {
        List<String> rawProduct = Arrays.stream(line.split(",")).toList();
        String name = rawProduct.get(0);
        int price = Integer.parseInt(rawProduct.get(1));
        int quantity = Integer.parseInt(rawProduct.get(2));
        String promotion = rawProduct.get(3);

        if (promotion.equals("null")) {
            promotion = null;
        }

        return new Product(name, price, quantity, promotion);
    }
}
