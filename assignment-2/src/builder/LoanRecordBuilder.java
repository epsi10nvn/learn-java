package builder;

import domain.Book;
import domain.Ebook;
import domain.LoanRecord;
import domain.Member;

import java.time.LocalDate;

public class LoanRecordBuilder {
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public LoanRecordBuilder member(Member member) {
        this.member = member;
        return this;
    }

    public LoanRecordBuilder book(Book book) {
        this.book = book;
        return this;
    }

    public LoanRecordBuilder borrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    public LoanRecordBuilder dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LoanRecordBuilder withDefaultDates() {
        if (book == null) {
            throw new IllegalStateException("Book must be set before calling withDefaultDates()");
        }
        this.borrowDate = LocalDate.now();
        int loanDays = (book instanceof Ebook) ? 30 : 14;
        this.dueDate = this.borrowDate.plusDays(loanDays);
        return this;
    }

    public LoanRecord build() {
        validate();
        return new LoanRecord(member, book, borrowDate, dueDate);
    }

    private void validate() {
        if (member == null) {
            throw new IllegalStateException("Member is required");
        }
        if (book == null) {
            throw new IllegalStateException("Book is required");
        }
        if (borrowDate == null) {
            throw new IllegalStateException("Borrow date is required");
        }
        if (dueDate == null) {
            throw new IllegalStateException("Due date is required");
        }
        if (dueDate.isBefore(borrowDate)) {
            throw new IllegalStateException("Due date cannot be before borrow date");
        }
    }
}

