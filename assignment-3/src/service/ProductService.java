package service;

import builder.ProductBuilder;
import model.Product;
import exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    public Product addProduct(String code, String name, double price, int stock) {
        Product existingProduct = products.stream()
                .filter(p -> p.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (existingProduct != null) {
            if (existingProduct.getName().equalsIgnoreCase(name)) {
                existingProduct.increaseStock(stock);
                return existingProduct;
            } else {
                throw new IllegalArgumentException("Product code already exists with different name: " + code);
            }
        }

        Product product = new ProductBuilder()
                .code(code)
                .name(name)
                .price(price)
                .stock(stock)
                .build();
        products.add(product);
        return product;
    }

    public Product findProductByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be null or empty");
        }
        return products.stream()
                .filter(p -> p.getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(code));
    }

    public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchName = name.toLowerCase().trim();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchName))
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}

