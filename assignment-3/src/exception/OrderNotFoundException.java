package exception;

public class OrderNotFoundException extends AppException {
    private final long orderId;

    public OrderNotFoundException(long orderId) {
        super("ORDER_NOT_FOUND", String.format("The order with ID '%d' was not found.", orderId));
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }
}

