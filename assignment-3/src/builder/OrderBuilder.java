package builder;

import model.Customer;
import model.Order;

public class OrderBuilder {
    private Customer customer;

    public OrderBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Order build() {
        validate();
        return new Order(customer);
    }

    private void validate() {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }
}

