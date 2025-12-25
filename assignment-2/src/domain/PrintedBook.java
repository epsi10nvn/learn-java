package domain;

import exception.BookOutOfStockException;

public class PrintedBook extends Book {
    private int totalCopies;
    private int availableCopies;
    private final String shelfLocation;

    public PrintedBook(
            String isbn,
            String title,
            String author,
            String publisher,
            int publicationYear,
            String category,
            int totalCopies,
            String shelfLocation) {
        super(isbn, title, author, publisher, publicationYear, category);
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.shelfLocation = shelfLocation;
    }

    @Override
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    @Override
    public String getBookType() {
        return "Printed Book";
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Printed Book Info ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Category: " + getCategory());
        System.out.println("Status: " + availableCopies + "/" + totalCopies + " available");
        System.out.println("Shelf Location: " + shelfLocation);
        System.out.println("=========================");
    }

    @Override
    public void borrow() {
        if (!isAvailable()) {
            throw new BookOutOfStockException(getId(), getTitle());
        }
        availableCopies--;
    }

    @Override
    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    public void addCopies(int quantity) {
        this.totalCopies += quantity;
        this.availableCopies += quantity;
    }
}
