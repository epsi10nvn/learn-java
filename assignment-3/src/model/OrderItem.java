package model;

public class OrderItem {
    private final Product product;
    private final int quantity;
    private final double unitPrice;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("OrderItem{product='%s', quantity=%d, unitPrice=%.2f, subtotal=%.2f}",
                product.getName(), quantity, unitPrice, getSubtotal());
    }
}

