package com.workintech.library.Model;

public class Librarian extends Person{
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void whoYouAre() {
        System.out.println(getName());
    }
}
