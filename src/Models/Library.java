package Models;

import Exceptions.BookExistException;
import Exceptions.BookNotFoundException;
import Exceptions.MemberExistException;
import Exceptions.MemberNotFoundException;

import java.util.ArrayList;

public class Library {
    private ArrayList<Person> members;
    private ArrayList<Book> books;

    public Library(){
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    /*
    * Adds a new person to the library's list of members
    * @param newMember the person to be added
    * @throws MemberExistException if the new person is already a member
    */
    public void addMember(Person newMember) throws MemberExistException {
        // check if there is a member with the same name
        for(int i=0; i < this.members.size(); i++){
            if(this.members.get(i).getName().equals(newMember.getName()))
                throw new MemberExistException("There is an existing member with this name");
        }
        // member doesn't exist. Add the new member to the list of members
        this.members.add(newMember);
    }

    /*
     * Removes a member from the library's list of members
     * @param name the name of the member to be removed
     * @return a confirmation message if the remove was successful
     * @throws MemberNotFoundException if no member is found with the name provided
     */
    public String removeMember(String name) throws MemberNotFoundException {
        // search for member to remove
        for(int i=0; i < this.members.size(); i++){
            if(this.members.get(i).getName().equals(name)){
                // member found. remove member and return confirmation message
                this.members.remove(i);
                return "Member Removed";
            }
        }
        // member not found. throw member not found exception
        throw new MemberNotFoundException("There is no existing member with the name provided");
    }

    /*
    * Adds a new book to the library's list of books
    * @param newBook the new book to be added
    * @throws BookExistException if the book already exist in the library
    */
    public void addBook(Book newBook) throws BookExistException {
        // check if there is a book with the same isbn
        for(int i=0; i < this.books.size(); i++){
            if(newBook.getIsbn().equals(this.books.get(i).getIsbn()))
                throw new BookExistException("There is an existing book with the same isbn");
        }
        // book not found. add book to library
        this.books.add(newBook);
    }

    /*
    * Removes a book from the library's list of books
    * @param isbn the isbn of the book to be removed
    * @return confirmation message in case book is removed successful
    * @throws BookNotFoundException if the book is not found
    * */
    public String removeBook(String isbn) throws BookNotFoundException {
        // search for a book with the provided isbn
        for(int i=0; i < this.books.size(); i++){
            if(isbn.equals(this.books.get(i).getIsbn())){
                // book found. remove from library and return confirmation message
                this.books.remove(i);
                return "Book Removed";
            }
        }
        // book not found. throw exception
        throw new BookNotFoundException("There is no book with the isbn provided");
    }

    /*
    * Add copies to an existing book in the library
    * @param isbn the isbn of the book to add copies to
    * @param copies the number of new copies to be added to the book provided
    * @return confirmation message in case copies added successful
    * @throws BookNotFoundException if no book is found with the isbn provided
    * */
    public String addCopies(String isbn, int copies) throws BookNotFoundException {
        // search for a book with the provided isbn
        for(int i=0; i < this.books.size(); i++){
            if(isbn.equals(this.books.get(i).getIsbn()))
                // book found. increment book's copies.
                this.books.get(i).setCopies(this.books.get(i).getCopies() + copies);
        }
        // book not found. throw exception
        throw new BookNotFoundException("There is no book found with the isbn provided");
    }

    /*
    * Search for a book by the title
    * @param title the book title to search for
    * @return the book if found
    * @throws BookNotFoundException if no book is found with the title provided
    * */
    public Book searchBookByTitle(String title) throws BookNotFoundException {
        // search for the book in the library's books
        for(int i=0; i < this.books.size(); i++){
            if(title.equals(this.books.get(i).getTitle()))
                // book found. return book
                return this.books.get(i);
        }
        // book not found. throw exception
        throw new BookNotFoundException("There is no book found with the title provided");
    }

    /*
     * Search for a book by the isbn
     * @param isbn the book isbn to search for
     * @return the book if found
     * @throws BookNotFoundException if no book is found with the isbn provided
     * */
    public Book searchBookByIsbn(String isbn) throws BookNotFoundException {
        // search for the book in the library's books
        for(int i=0; i < this.books.size(); i++){
            if(isbn.equals(this.books.get(i).getIsbn()))
                // book found. return book
                return this.books.get(i);
        }
        // book not found. throw exception
        throw new BookNotFoundException("There is no book found with the isbn provided");
    }
}
