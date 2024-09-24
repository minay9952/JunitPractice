package Models;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private ArrayList<Book> borrowedBooks;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
