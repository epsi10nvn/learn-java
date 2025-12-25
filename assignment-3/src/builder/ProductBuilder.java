package builder;

import model.Product;

public class ProductBuilder {
    private String code;
    private String name;
    private double price;
    private int stock;

    public ProductBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder stock(int stock) {
        this.stock = stock;
        return this;
    }

    public Product build() {
        validate();
        return new Product(code, name, price, stock);
    }

    private void validate() {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock must be greater or equals 0");
        }
    }
}

