package service;

import domain.Book;
import domain.Ebook;
import domain.LoanRecord;
import domain.Member;
import exception.custom_exception.BookNotFoundException;
import exception.custom_exception.BorrowLimitExceededException;
import exception.custom_exception.MemberNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<LoanRecord> loans = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    private final int MAX_LOAN = 5;

    public void addMember(Member member) {
        members.add(member);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Member searchMemberById(String memberId) throws MemberNotFoundException {
        return members.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    public Book searchBookById(String id) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> searchBooksByTitle(String title) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByCategory(String category) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void deleteBookById(String id) throws BookNotFoundException {
        Book book = searchBookById(id);
        books.remove(book);
    }

    public void borrowBooks(String bookId, String memberId) throws Exception {
        Book book = searchBookById(bookId);
        Member member = searchMemberById(memberId);

        long count = loans.stream()
                .filter(loan -> loan.getMember().getMemberName().equalsIgnoreCase(memberId)
                        && loan.getBook().getId().equals(bookId))
                .count();


        if (count >= MAX_LOAN) {
            throw new BorrowLimitExceededException(memberId, (int) count, MAX_LOAN);
        }

        book.borrow();

        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(book instanceof Ebook ? 30 : 14);

        loans.add(new LoanRecord(member, book, today, dueDate));
    }

    public void returnBooks(String bookId, String memberId) throws Exception {
        Book book = searchBookById(bookId);
        Member member = searchMemberById(memberId);

        LoanRecord loanRecord = loans.stream()
                .filter(loan -> loan.getMember().getId().equalsIgnoreCase(memberId)
                        && loan.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new Exception("No loan record found for member " + memberId + " and book " + bookId));

        book.returnBook();
        loans.remove(loanRecord);
    }

    public void showBooks() {
        books.forEach(Book::displayInfo);
    }

    public void showLoans() {
        loans.forEach(LoanRecord::displayInfo);
    }

    public void showMembers() {
        members.forEach(Member::displayInfo);
    }

}
