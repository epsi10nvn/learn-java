package domain;

public class Ebook extends Book {

    private final double fileSizeMB;
    private final String format;

    public Ebook(
            String isbn,
            String title,
            String author,
            String publisher,
            int publicationYear,
            String category,
            double fileSizeMB,
            String format
    ) {
        super(isbn, title, author, publisher, publicationYear, category);
        this.fileSizeMB = fileSizeMB;
        this.format = format;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getBookType() {
        return "Ebook";
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Ebook Info ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Category: " + getCategory());
        System.out.println("Status: available");
        System.out.println("File size (MB): " + getFileSizeMB());
        System.out.println("Format: " + getFormat());
        System.out.println("=========================");
    }

    @Override
    public void borrow() {

    }

    @Override
    public void returnBook() {

    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public String getFormat() {
        return format;
    }
}
