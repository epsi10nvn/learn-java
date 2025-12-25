package service;

import builder.CustomerBuilder;
import model.Customer;
import exception.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private final List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    public Customer addCustomer(String code, String name, String email) {
        boolean codeExists = customers.stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(code));
        if (codeExists) {
            throw new IllegalArgumentException("Customer code already exists: " + code);
        }

        boolean emailExists = customers.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
        if (emailExists) {
            throw new IllegalArgumentException("Customer email already exists: " + email);
        }

        Customer customer = new CustomerBuilder()
                .code(code)
                .name(name)
                .email(email)
                .build();
        customers.add(customer);
        return customer;
    }

    public Customer findCustomerByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer code cannot be null or empty");
        }
        return customers.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(code));
    }

    public List<Customer> getTopCustomersBySpending(int topN) {
        if (topN <= 0) {
            throw new IllegalArgumentException("Top N must be greater than 0");
        }
        return customers.stream()
                .sorted(Comparator.comparing(Customer::getTotalSpending).reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}

