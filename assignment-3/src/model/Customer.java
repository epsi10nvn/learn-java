package model;

public class Customer {
    private static long AUTO_ID = 1;

    private final long id;
    private final String code;
    private final String name;
    private final String email;
    private double totalSpending;

    public Customer(String code, String name, String email) {
        this.id = AUTO_ID++;
        this.code = code;
        this.name = name;
        this.email = email;
        this.totalSpending = 0.0;
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

    public String getEmail() {
        return email;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void addSpending(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Spending amount cannot be negative");
        }
        this.totalSpending += amount;
    }

    @Override
    public String toString() {
        return String.format("Customer{id=%d, code='%s', name='%s', email='%s', totalSpending=%.2f}",
                id, code, name, email, totalSpending);
    }
}

