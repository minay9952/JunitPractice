package Models;

import java.util.ArrayList;

public class Library {
    private ArrayList<Person> members;
    private ArrayList<Book> books;

    public Library(){
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }
}
