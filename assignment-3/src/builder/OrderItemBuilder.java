package builder;

import model.OrderItem;
import model.Product;

public class OrderItemBuilder {
    private Product product;
    private int quantity;

    public OrderItemBuilder product(Product product) {
        this.product = product;
        return this;
    }

    public OrderItemBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItem build() {
        validate();
        return new OrderItem(product, quantity);
    }

    private void validate() {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}

