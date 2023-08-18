package com.workintech.library.Model;

import java.util.List;

public class Author extends Person {

    private List<Book> books;

    public Author(String name, List<Book> books) {
        super(name);
        this.books = books;
    }


    public List<Book> getBooks() {
        return books;
    }


    @Override
    public void whoYouAre() {
        System.out.println(getName());
    }
}
