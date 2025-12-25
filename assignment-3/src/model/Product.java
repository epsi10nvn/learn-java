package model;

import exception.InsufficientStockException;

public class Product {
    private static long AUTO_ID = 1;

    private final long id;
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.id = AUTO_ID++;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (quantity > this.stock) {
            throw new InsufficientStockException(this.id, this.stock, quantity);
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, code='%s', name='%s', price=%.2f, stock=%d}",
                id, code, name, price, stock);
    }
}
