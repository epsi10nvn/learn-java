package domain;

import java.util.UUID;

public abstract class Book {
    private final String id;
    private final String isbn;
    private final String title;
    private final String author;
    private final String publisher;
    private final int publicationYear;
    private final String category;

    public Book(
            String isbn,
            String title,
            String author,
            String publisher,
            int publicationYear,
            String category
    ) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
    }

    public abstract boolean isAvailable();

    public abstract String getBookType();

    public abstract void displayInfo();

    public abstract void borrow();

    public abstract void returnBook();

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format(
                "[TYPE=%s] %s - Author: %s, Publication year: %s",
                getBookType(), title, author, publicationYear
        );
    }
}
