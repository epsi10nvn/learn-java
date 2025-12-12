package exception.custom_exception;

import exception.AppException;

public class BookOutOfStockException extends AppException {
    private final String bookId;
    private final String bookTitle;

    public BookOutOfStockException(String bookId, String bookTitle) {
        super("BOOK_OUT_OF_STOCK", String.format("The book '%s' (ID: %s) is out of stock.", bookTitle, bookId));
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
