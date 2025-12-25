package builder;

import domain.Book;

public abstract class BookBuilder<T extends BookBuilder<T, B>, B extends Book> {
    // Common book fields
    protected String isbn;
    protected String title;
    protected String author;
    protected String publisher;
    protected Integer publicationYear;
    protected String category;

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public T isbn(String isbn) {
        this.isbn = isbn;
        return self();
    }

    public T title(String title) {
        this.title = title;
        return self();
    }

    public T author(String author) {
        this.author = author;
        return self();
    }

    public T publisher(String publisher) {
        this.publisher = publisher;
        return self();
    }

    public T publicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return self();
    }

    public T category(String category) {
        this.category = category;
        return self();
    }

    protected void validateCommonFields() {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalStateException("ISBN is required");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalStateException("Title is required");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalStateException("Author is required");
        }
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new IllegalStateException("Publisher is required");
        }
        if (publicationYear == null || publicationYear <= 0) {
            throw new IllegalStateException("Publication year must be a positive number");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalStateException("Category is required");
        }
    }

    public abstract B build();
}
