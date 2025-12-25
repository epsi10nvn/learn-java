package builder;

import domain.PrintedBook;

public class PrintedBookBuilder extends BookBuilder<PrintedBookBuilder, PrintedBook> {
    // PrintedBook specific fields
    private Integer totalCopies;
    private String shelfLocation;

    public PrintedBookBuilder totalCopies(int totalCopies) {
        if (totalCopies <= 0) {
            throw new IllegalArgumentException("Total copies must be greater than 0");
        }
        this.totalCopies = totalCopies;
        return this;
    }

    public PrintedBookBuilder shelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
        return this;
    }

    @Override
    public PrintedBook build() {
        validate();
        return new PrintedBook(
            isbn,
            title,
            author,
            publisher,
            publicationYear,
            category,
            totalCopies,
            shelfLocation
        );
    }

    private void validate() {
        validateCommonFields();
        
        if (totalCopies == null || totalCopies <= 0) {
            throw new IllegalStateException("Total copies must be greater than 0");
        }
        if (shelfLocation == null || shelfLocation.trim().isEmpty()) {
            throw new IllegalStateException("Shelf location is required");
        }
    }
}

