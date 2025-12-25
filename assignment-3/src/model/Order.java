package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long AUTO_ID = 1;

    private final long id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(Customer customer) {
        this.id = AUTO_ID++;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Order item cannot be null");
        }
        if (status != OrderStatus.PENDING) {
            throw new IllegalArgumentException("Cannot add items to a non-pending order");
        }
        items.add(item);
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public void confirm() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalArgumentException("Only pending orders can be confirmed");
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cannot confirm an empty order");
        }
        status = OrderStatus.CONFIRMED;
    }

    @Override
    public String toString() {
        return String.format("Order{id=%d, customer='%s', status=%s, total=%.2f, items=%d}",
                id, customer.getName(), status, getTotal(), items.size());
    }
}

