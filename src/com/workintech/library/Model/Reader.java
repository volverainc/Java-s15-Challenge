package com.workintech.library.Model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {

    private List<Book> books = new ArrayList<>();
    private double fees = 0.0;

    public Reader(String name) {
        super(name);
    }

    @Override
    public void whoYouAre() {
        System.out.println(getName());
    }

    public List<Book> getBooksBorrowed() {
        return books;
    }

    public boolean hasBorrowedBook(Book book) {
        return books.contains(book);
    }

    public void borrowBook(Book book) {
        books.add(book);
    }

    public void returnBook(Book book) {
        books.remove(book);
    }

    public double getFees() {
        return fees;
    }

    public void chargeFee(double fee) {
        fees += fee;
    }

    public void refundFee(double fee) {
        fees -= fee;
        if (fees < 0) {
            fees = 0;
        }
    }
}