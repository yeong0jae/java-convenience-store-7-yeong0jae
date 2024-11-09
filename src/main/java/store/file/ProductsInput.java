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

        for (String line : lines) {
            Product product = parseProduct(line);
            products.add(product);
        }
        return products;
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
