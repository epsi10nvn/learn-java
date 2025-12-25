package exception;

public class ProductNotFoundException extends AppException {
    private final String productCode;

    public ProductNotFoundException(String productCode) {
        super("PRODUCT_NOT_FOUND", String.format("The product with code '%s' was not found.", productCode));
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }
}
