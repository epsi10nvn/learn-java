package domain;

import java.time.LocalDate;

public class LoanRecord {
    private final Member member;
    private final Book book;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public LoanRecord(Member member, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public void displayInfo() {
        System.out.println("=== Loan Record ===");
        System.out.println("Member Name: " + member.getMemberName());
        System.out.println("Book Title: " + book.getTitle());
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("===================");
    }
}
