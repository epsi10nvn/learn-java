import domain.Ebook;
import domain.Member;
import domain.PrintedBook;
import service.Library;
import utils.InputUtils;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("0. Add new member");
            System.out.println("1. Add new book");
            System.out.println("2. Search book");
            System.out.println("3. Remove book");
            System.out.println("4. Loan book");
            System.out.println("5. Return book");
            System.out.println("6. Get all members");
            System.out.println("7. Get all books");
            System.out.println("8. Get all loan records");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(InputUtils.nextLine(""));

                switch (choice) {
                    case 0:
                        addMemberMenu(lib);
                        break;
                    case 1:
                        addBookMenu(lib);
                        break;
                    case 2:
                        searchMenu(lib);
                        break;
                    case 3:
                        lib.deleteBookById(InputUtils.nextLine("Enter book id: "));
                        break;
                    case 4:
                        lib.borrowBooks(
                                InputUtils.nextLine("Book id: "),
                                InputUtils.nextLine("Member id: ")
                        );
                        break;
                    case 5:
                        lib.returnBooks(
                                InputUtils.nextLine("Book id: "),
                                InputUtils.nextLine("Member id: ")
                        );
                        break;
                    case 6:
                        lib.showMembers();
                        break;
                    case 7:
                        lib.showBooks();
                        break;
                    case 8:
                        lib.showLoans();
                        break;
                    case 9:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addMemberMenu(Library lib) {
        String memberName = InputUtils.nextLine("Member Name: ");

        lib.addMember(new Member(memberName));
    }

    private static void addBookMenu(Library lib) {
        System.out.println("1. Printed Book");
        System.out.println("2. Ebook");
        int t = InputUtils.nextInt("Select book type: ");

        String isbn = InputUtils.nextLine("ISBN: ");
        String title = InputUtils.nextLine("Title: ");
        String author = InputUtils.nextLine("Author: ");
        String pub = InputUtils.nextLine("Publisher: ");
        int pubYear = InputUtils.nextInt("Publication year: ");
        String category  = InputUtils.nextLine("Category: ");

        if (t == 1) {
            int totalCopies = InputUtils.nextInt("Stock: ");
            String shelfLocation = InputUtils.nextLine("Shelf location: ");
            lib.addBook(new PrintedBook(isbn, title, author, pub, pubYear, category, totalCopies, shelfLocation));

        } else {
            double size = InputUtils.nextDouble("File size: ");
            String format = InputUtils.nextLine("Format (PDF/EPUB): ");
            lib.addBook(new Ebook(isbn, title, author, pub, pubYear, category, size, format));
        }
    }

    private static void searchMenu(Library lib) {
        System.out.println("1. By ID");
        System.out.println("2. By Title");
        System.out.println("3. By Category");

        int s = InputUtils.nextInt("Choice: ");

        switch (s) {
            case 1:
                try {
                    System.out.println(lib.searchBookById(InputUtils.nextLine("ID: ")));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case 2:
                lib.searchBooksByTitle(InputUtils.nextLine("Title: "))
                        .forEach(System.out::println);
                break;
            case 3:
                lib.searchBooksByCategory(InputUtils.nextLine("Category: "))
                        .forEach(System.out::println);
                break;
        }
    }
}
