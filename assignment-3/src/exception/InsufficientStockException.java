package exception;

public class InsufficientStockException extends AppException {
    private final long productId;
    private final int availableStock;
    private final int requestedQuantity;

    public InsufficientStockException(long productId, int availableStock, int requestedQuantity) {
        super("INSUFFICIENT_STOCK",
                String.format("Insufficient stock for product ID '%d'. Available: %d, Requested: %d",
                        productId, availableStock, requestedQuantity));
        this.productId = productId;
        this.availableStock = availableStock;
        this.requestedQuantity = requestedQuantity;
    }

    public long getProductId() {
        return productId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }
}
