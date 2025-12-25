package exception;

public class LoanRecordNotFoundException extends AppException {
    private final String bookId;
    private final String memberId;

    public LoanRecordNotFoundException(String bookId, String memberId) {
        super("LOAN_RECORD_NOT_FOUND", String.format(
                "No loan record found for member ID '%s' and book ID '%s'.", memberId, bookId));
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }
}

