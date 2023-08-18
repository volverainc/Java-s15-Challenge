package com.workintech.library.Main;

import com.workintech.library.Enums.BookStatus;
import com.workintech.library.Model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String password = null;

        boolean enteredName = false;
        boolean enteredPassword = false;

        while (!(enteredName && enteredPassword)) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();
            enteredName = !name.isEmpty();

            System.out.print("Enter your password: ");
            password = scanner.nextLine().trim();
            enteredPassword = !password.isEmpty();

            if (!enteredName || !enteredPassword) {
                System.out.println("Please enter your login credentials.");
            }
        }

        Librarian librarian = new Librarian(name, password);
        System.out.println(librarian.getName() + " has logged in.");
        List<Book> books = new ArrayList<>();
        List<Librarian> librarians = new ArrayList<>();
        Set<Reader> readers = new HashSet<>();

        Library library = new Library(books, librarians, readers);

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1 - Add Book");
            System.out.println("2 - Search Books");
            System.out.println("3 - List Books");
            System.out.println("4 - Issue Books");
            System.out.println("5 - Reissue Books");
            System.out.println("6 - Update Books");
            System.out.println("7 - Remove Books");
            System.out.println("8 - Lookup Readers");
            System.out.println("9 - Add Readers");
            System.out.println("10 - Remove Readers");
            System.out.println("11 - List All Readers");
            System.out.println("12 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Book newBook = createBook(scanner);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    library.searchBooks();
                    break;

                case 3:
                    library.listBooks();
                    break;

                case 4:
                    library.issueBooks();
                    break;

                case 5:
                    library.reissueBooks();
                    break;

                case 6:
                    System.out.print("Enter the book ID to update: ");
                    long bookIDToUpdate = scanner.nextLong();
                    scanner.nextLine();
                    Book bookToUpdate = library.selectBookByID(bookIDToUpdate);

                    if (bookToUpdate != null) {
                        updateBookDetails(scanner, bookToUpdate);
                        System.out.println("Book updated successfully.");
                    } else {
                        System.out.println("Book not found with ID: " + bookIDToUpdate);
                    }
                    break;

                case 7:
                    System.out.print("Enter the book ID to remove: ");
                    long bookIDToRemove = scanner.nextLong();
                    scanner.nextLine();
                    library.removeBook(bookIDToRemove);
                    System.out.println("Book removed successfully.");
                    break;

                case 8:
                    library.lookupReaders();
                    break;

                case 9:
                    library.addReaders();
                    break;

                case 10:
                    library.removeReaders();
                    break;

                case 11:
                    library.listAllReaders();
                    break;

                case 12:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static Book createBook(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        long bookID = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Edition: ");
        int edition = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Category: ");
        String bookCategories = scanner.nextLine();

        LocalDate dateOfPurchase = LocalDate.now();
        Author author = new Author(authorName, null);

        return new Book(bookID, author, bookName, price, BookStatus.AVAILABLE, edition, dateOfPurchase,bookCategories);
    }

    private static void updateBookDetails(Scanner scanner, Book bookToUpdate) {
        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Edition: ");
        int edition = scanner.nextInt();
        scanner.nextLine();

        Author newAuthor = new Author(authorName, null);

        bookToUpdate.setName(bookName);
        bookToUpdate.setAuthor(newAuthor);
        bookToUpdate.setPrice(price);
        bookToUpdate.setEdition(edition);
    }
}