package exception;

public class CustomerNotFoundException extends AppException {
    private final String customerCode;

    public CustomerNotFoundException(String customerCode) {
        super("CUSTOMER_NOT_FOUND", String.format("The customer with code '%s' was not found.", customerCode));
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }
}
