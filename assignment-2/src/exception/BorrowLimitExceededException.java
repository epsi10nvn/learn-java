package exception;

public class BorrowLimitExceededException extends AppException {
    private final String memberName;
    private final long currentBorrowedBooks;
    private final long borrowLimit;

    public BorrowLimitExceededException(String memberName, long currentBorrowedBooks, long borrowLimit) {
        super("BORROW_LIMIT_EXCEEDED", String.format(
                "Member ID: %s has exceeded the borrow limit. Current borrowed books: %d, Borrow limit: %d.",
                memberName, currentBorrowedBooks, borrowLimit));
        this.memberName = memberName;
        this.currentBorrowedBooks = currentBorrowedBooks;
        this.borrowLimit = borrowLimit;
    }
}
