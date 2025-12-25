package exception;

public class InvalidOrderStateException extends AppException {
    private final String currentState;
    private final String expectedState;

    public InvalidOrderStateException(String currentState, String expectedState) {
        super("INVALID_ORDER_STATE",
                String.format("Invalid order state. Current: %s, Expected: %s", currentState, expectedState));
        this.currentState = currentState;
        this.expectedState = expectedState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getExpectedState() {
        return expectedState;
    }
}

