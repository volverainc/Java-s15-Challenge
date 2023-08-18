package com.workintech.library.Model;

import com.workintech.library.Enums.BookStatus;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library implements Management{

    private Map<Long, Book> bookMap;
    private List<Librarian> librarians;
    private Set<Reader> readerSet;

    public Library(List<Book> books, List<Librarian> librarians, Set<Reader> readerSet) {
        AllBooks(books);
        this.librarians = librarians;
        this.readerSet = readerSet;
    }

    private void AllBooks(List<Book> books) {
        bookMap = new HashMap<>();
        for (Book book : books) {
            bookMap.put(book.getBookID(), book);
        }
    }

    public void addBook(Book book) {
        bookMap.put(book.getBookID(), book);
    }

    public void removeBook(long bookID) {
        bookMap.remove(bookID);
    }

    public Book selectBookByID(long bookID) {
        return bookMap.get(bookID);
    }

    public List<Book> selectBooksByName(String name) {
        List<Book> selectedBooks = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getName().equalsIgnoreCase(name)) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    public List<Book> selectBooksByAuthor(Author author) {
        List<Book> selectedBooks = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getAuthor().equals(author)) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    public List<Book> selectBooksByCategory(String bookCategories) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getBookCategories().equals(bookCategories)) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    public void searchBooks() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search books by:");
        System.out.println("1 - Author Name");
        System.out.println("2 - Book Name");
        System.out.println("3 - Book ID");
        System.out.println("4 - Book Categpry");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1:
                System.out.print("Enter author name: ");
                String authorName = scanner.nextLine();
                List<Book> booksByAuthor = selectBooksByAuthor(new Author(authorName, null));//??
                displayBooks(booksByAuthor);
                break;

            case 2:
                System.out.print("Enter book name: ");
                String bookName = scanner.nextLine();
                List<Book> booksByName = selectBooksByName(bookName);
                displayBooks(booksByName);
                break;

            case 3:
                System.out.print("Enter book ID: ");
                long bookID = scanner.nextLong();
                Book bookByID = selectBookByID(bookID);
                if (bookByID != null) {
                    System.out.println("Book found:");
                    displayBook(bookByID);
                } else {
                    System.out.println("Book not found with ID: " + bookID);
                }
                break;

            case 4:
                System.out.println("Enter book Category: ");
                String bookCategory = scanner.nextLine();
                List<Book> booksByCategory = selectBooksByCategory(bookCategory);
                displayBooks(booksByCategory);
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void displayBooks(List<Book> books) {
        if (!books.isEmpty()) {
            System.out.println("Matching books:");
            for (Book book : books) {
                displayBook(book);
                System.out.println("*****************");
            }
        } else {
            System.out.println("No books matched.");
        }
    }

    private void displayBook(Book book) {
        System.out.println("Book ID: " + book.getBookID());
        System.out.println("Author: " + book.getAuthor().getName());
        System.out.println("Name: " + book.getName());
        System.out.println("Price: " + book.getPrice());
        System.out.println("Book Status: " + book.getBookStatus());
        System.out.println("Edition: " + book.getEdition());
        System.out.println("Date of Purchase: " + book.getDateOfPurchase());
        System.out.println("Category: " + book.getBookCategories());
    }

    public void updateBook(long bookID, String name, Author author, double price, BookStatus bookStatus, int edition, String dateOfPurchase,String bookCategories) {
        Book book = bookMap.get(bookID);
        if (book != null) {
            book.setName(name);
            book.setAuthor(author);
            book.setPrice(price);
            book.setBookStatus(bookStatus);
            book.setEdition(edition);
            book.setBookCategories(bookCategories);
        }
    }



    public void listBooks() {
        System.out.println("List of all books in the library:");
        for (Book book : bookMap.values()) {
            System.out.println("Book ID: " + book.getBookID());
            System.out.println("Author: " + book.getAuthor().getName());
            System.out.println("Name: " + book.getName());
            System.out.println("Price: " + book.getPrice());
            System.out.println("Book Status: " + book.getBookStatus());
            System.out.println("Edition: " + book.getEdition());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = book.getDateOfPurchase().format(formatter);
            System.out.println("Date of Purchase: " + formattedDate);
            System.out.println("Category: " + book.getBookCategories());
            System.out.println("****************");
        }
        }

    public void listAllReaders() {
        System.out.println("List of all readers:");
        for (Reader reader : readerSet) {
            System.out.println("Reader Name: " + reader.getName());
            System.out.println("************************");
            List<Book> borrowedBooks = reader.getBooksBorrowed();
            if (borrowedBooks.isEmpty()) {
                System.out.println("No books have been issued to "+ reader.getName());
            } else {
                for (Book book : borrowedBooks) {
                    System.out.println(book.getName() + " issued to " + reader.getName());
                }
            }
            System.out.println("Fees: " + reader.getFees());
            System.out.println("************************");
        }
    }

    public void removeReaders() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the reader's name: ");
        String readerName = scanner.nextLine();

        Reader foundReader = null;
        for (Reader reader : readerSet) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                foundReader = reader;
                break;
            }
        }
        if (foundReader != null) {
            readerSet.remove(foundReader);
            System.out.println("Reader removed successfully.");
        } else {
            System.out.println("Reader not found with the name: " + readerName);
        }
    }

    public void addReaders() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new reader's name: ");
        String readerName = scanner.nextLine();
        Reader newReader = new Reader(readerName);
        readerSet.add(newReader);
        System.out.println("New reader added successfully.");
    }

    public void lookupReaders() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the reader's name to look up: ");
        String readerName = scanner.nextLine();
        Reader foundReader = null;
        for (Reader reader : readerSet) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                foundReader = reader;
                break;
            }
        }

        if (foundReader != null) {
            System.out.println("Reader Found!");
            System.out.println("Reader Name: " + foundReader.getName());
            System.out.println("Books Borrowed:");
            List<Book> borrowedBooks = foundReader.getBooksBorrowed();
            if (borrowedBooks.isEmpty()) {
                System.out.println(foundReader.getName() + " not holding any books.");
            } else {
                for (Book book : borrowedBooks) {
                    System.out.println(book.getName() + " issued to " + foundReader.getName());
                }
            }
            System.out.println("Fees: $" + foundReader.getFees());
        } else {
            System.out.println("Reader not found with the name: " + readerName);
        }
    }

    public void issueBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the reader's name: ");
        String readerName = scanner.nextLine();
        Reader foundReader = null;
        for (Reader reader : readerSet) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                foundReader = reader;
                break;
            }
        }

        if (foundReader != null) {
            if (foundReader.getBooksBorrowed().size() >= 5) {
                System.out.println("The reader has already borrowed the maximum number of books.");
                return;
            }
            System.out.print("Enter the book ID to issue: ");
            long bookID = scanner.nextLong();
            scanner.nextLine();
            Book bookToIssue = bookMap.get(bookID);
            if (bookToIssue != null && bookToIssue.getBookStatus() == BookStatus.AVAILABLE) {
                foundReader.borrowBook(bookToIssue);
                bookToIssue.setBookStatus(BookStatus.BORROWED);
                double fee = bookToIssue.getPrice();
                foundReader.chargeFee(fee);
                System.out.println("Book issued successfully to " + foundReader.getName() + " and " + fee + " has been charged.");
            } else {
                System.out.println("Book not available.");
            }
        } else {
            System.out.println("Reader not found with the name: " + readerName);
        }
    }

    public void reissueBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the reader's name: ");
        String readerName = scanner.nextLine();
        Reader foundReader = null;
        for (Reader reader : readerSet) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                foundReader = reader;
                break;
            }
        }

        if (foundReader != null) {
            System.out.print("Enter the book ID to reissue: ");
            long bookID = scanner.nextLong();
            scanner.nextLine();
            Book bookToReissue = bookMap.get(bookID);
            if (bookToReissue != null && bookToReissue.getBookStatus() == BookStatus.BORROWED && foundReader.hasBorrowedBook(bookToReissue)) {
                bookToReissue.setBookStatus(BookStatus.AVAILABLE);
                double fee = bookToReissue.getPrice();
                foundReader.refundFee(fee);
                foundReader.returnBook(bookToReissue);
                System.out.println("Book reissued successfully for " + foundReader.getName());
            } else {
                System.out.println("Book not available for reissuing.");
            }
        } else {
            System.out.println("Reader not found with the name: " + readerName);
        }
    }

}

