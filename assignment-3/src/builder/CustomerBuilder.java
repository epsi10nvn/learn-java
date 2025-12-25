package builder;

import model.Customer;

public class CustomerBuilder {
    private String code;
    private String name;
    private String email;

    public CustomerBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Customer build() {
        validate();
        return new Customer(code, name, email);
    }

    private void validate() {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer code cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String trimmedEmail = email.trim();
        int atIndex = trimmedEmail.indexOf('@');
        if (atIndex <= 0 || atIndex >= trimmedEmail.length() - 1) {
            return false;
        }
        String domain = trimmedEmail.substring(atIndex + 1);
        int dotIndex = domain.lastIndexOf('.');
        return dotIndex > 0 && dotIndex < domain.length() - 1;
    }
}

