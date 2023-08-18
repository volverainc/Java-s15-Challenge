package com.workintech.library.Model;

import com.workintech.library.Enums.BookStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private long bookID;
    private Author author;
    private String name;
    private double price;
    private BookStatus bookStatus;
    private int edition;
    private LocalDate dateOfPurchase;
    private String bookCategories;

    public Book(long bookID, Author author, String name, double price, BookStatus bookStatus, int edition, LocalDate dateOfPurchase, String bookCategories) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.bookStatus = bookStatus;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;;
        this.bookCategories = bookCategories;
    }

    public void setBookCategories(String bookCategories) {
        this.bookCategories = bookCategories;
    }

    public String getBookCategories() {
        return bookCategories;
    }

    public long getBookID() {
        return bookID;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public int getEdition() {
        return edition;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookID == book.bookID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", bookStatus=" + bookStatus +
                ", edition=" + edition +
                ", dateOfPurchase=" + dateOfPurchase +
                ", bookCategories='" + bookCategories + '\'' +
                '}';
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

}
