package service;

import builder.OrderBuilder;
import builder.OrderItemBuilder;
import model.*;
import exception.InsufficientStockException;
import exception.InvalidOrderStateException;
import exception.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final List<Order> orders;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderService(ProductService productService, CustomerService customerService) {
        this.orders = new ArrayList<>();
        this.productService = productService;
        this.customerService = customerService;
    }

    public Order createOrder(String customerCode) {
        Customer customer = customerService.findCustomerByCode(customerCode);
        Order order = new OrderBuilder()
                .customer(customer)
                .build();
        orders.add(order);
        return order;
    }

    public void addProductToOrder(long orderId, String productCode, int quantity) {
        Order order = findOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStateException(
                    order.getStatus().name(),
                    OrderStatus.PENDING.name()
            );
        }

        Product product = productService.findProductByCode(productCode);
        
        if (product.getStock() < quantity) {
            throw new InsufficientStockException(product.getId(), product.getStock(), quantity);
        }

        OrderItem item = new OrderItemBuilder()
                .product(product)
                .quantity(quantity)
                .build();
        order.addItem(item);
    }

    public void confirmOrder(long orderId) {
        Order order = findOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStateException(
                    order.getStatus().name(),
                    OrderStatus.PENDING.name()
            );
        }

        // Check stock availability for all items
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException(
                        product.getId(),
                        product.getStock(),
                        item.getQuantity()
                );
            }
        }

        // Deduct stock and update customer spending
        for (OrderItem item : order.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        double total = order.getTotal();
        order.getCustomer().addSpending(total);
        order.confirm();
    }

    public List<Order> getOrdersByCustomer(String customerCode) {
        customerService.findCustomerByCode(customerCode);
        return orders.stream()
                .filter(o -> o.getCustomer().getCode().equalsIgnoreCase(customerCode))
                .collect(Collectors.toList());
    }

    public double getTotalRevenueFromConfirmedOrders() {
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.CONFIRMED)
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public Order findOrderById(long orderId) {
        return orders.stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}

