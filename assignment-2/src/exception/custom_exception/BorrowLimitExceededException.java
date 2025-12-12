package exception.custom_exception;

import exception.AppException;

public class BorrowLimitExceededException extends AppException {
    private final String memberName;
    private final int currentBorrowedBooks;
    private final int borrowLimit;

    public BorrowLimitExceededException(String memberName, int currentBorrowedBooks, int borrowLimit) {
        super("BORROW_LIMIT_EXCEEDED", String.format(
                "Member ID: %s has exceeded the borrow limit. Current borrowed books: %d, Borrow limit: %d.",
                memberName, currentBorrowedBooks, borrowLimit));
        this.memberName = memberName;
        this.currentBorrowedBooks = currentBorrowedBooks;
        this.borrowLimit = borrowLimit;
    }
}
