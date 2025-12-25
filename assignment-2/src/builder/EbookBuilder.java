package builder;

import domain.Ebook;

public class EbookBuilder extends BookBuilder<EbookBuilder, Ebook> {
    // Ebook specific fields
    private Double fileSizeMB;
    private String format;

    public EbookBuilder fileSizeMB(double fileSizeMB) {
        if (fileSizeMB <= 0) {
            throw new IllegalArgumentException("File size must be greater than 0");
        }
        this.fileSizeMB = fileSizeMB;
        return this;
    }

    public EbookBuilder format(String format) {
        if (format != null && !format.equalsIgnoreCase("PDF") && !format.equalsIgnoreCase("EPUB")) {
            throw new IllegalArgumentException("Format must be PDF or EPUB");
        }
        this.format = format;
        return this;
    }

    @Override
    public Ebook build() {
        validate();
        return new Ebook(
            isbn,
            title,
            author,
            publisher,
            publicationYear,
            category,
            fileSizeMB,
            format
        );
    }

    private void validate() {
        validateCommonFields();
        
        if (fileSizeMB == null || fileSizeMB <= 0) {
            throw new IllegalStateException("File size must be greater than 0");
        }
        if (format == null || format.trim().isEmpty()) {
            throw new IllegalStateException("Format is required");
        }
    }
}

