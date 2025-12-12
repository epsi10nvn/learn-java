package exception.custom_exception;

import exception.AppException;

public class BookNotFoundException extends AppException {
    private final String bookId;

    public BookNotFoundException(String bookId) {
        super("BOOK_NOT_FOUND", String.format("The book with ID '%s' was not found.", bookId));
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
