package Test;

import Exceptions.BookExistException;
import Exceptions.BookNotFoundException;
import Exceptions.MemberExistException;
import Exceptions.MemberNotFoundException;
import Models.Book;
import Models.Library;
import Models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Library Test Class")
public class LibraryTest {

    private static Library library;

    @BeforeEach
    public void initializeTest() {
        library = new Library();
    }

    @DisplayName("Add new member")
    @Test
    public void addNewMember() {
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            ArrayList<Person> members = library.getMembers();
            for(int i=0; i < members.size(); i++){
                assertTrue(members.get(i).getName().equals(member.getName()), "Member added Successfully");
                return;
            }
            fail("Member was not found in the library after adding him");
        }
        catch (Exception e) {
            fail("An Exception was thrown: "+e.getMessage());
        }
    }

    @DisplayName("Add an existing member")
    @Test
    public void addExistingMember() {
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            library.addMember(member);
            fail("An existing member was added again to the library");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberExistException);
        }
    }

    @DisplayName("Remove existing member")
    @Test
    public void removeExistingMember() {
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            assertTrue(library.removeMember(member.getName()).equals("Member Removed"));
        }
        catch(Exception e){
            fail("An exception was thrown: "+e.getMessage());
        }
    }

    @DisplayName("Remove non existing member")
    @Test
    public void removeNonExistingMember() {
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            library.removeMember("Ahmed");
            fail("a non existing member was removed from the library");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Add a new book")
    @Test
    public void addNewBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            ArrayList<Book> books = library.getBooks();
            for(var i=0; i < books.size(); i++){
                assertTrue(book.getIsbn().equals(books.get(i).getIsbn()));
                return;
            }
            fail("The added book was not found in the library");
        }
        catch (Exception e){
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Add an existing book")
    @Test
    public void addExistingBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try{
            library.addBook(book);
            library.addBook(book);
            fail("an existing book was added successfully to the library");
        }
        catch(Exception e){
            assertTrue(e instanceof BookExistException);
        }
    }

    @DisplayName("Remove an existing book")
    @Test
    public void removeExistingBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try{
            library.addBook(book);
            assertTrue(library.removeBook(book.getIsbn()).equals("Book Removed"));
        }
        catch (Exception e){
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Remove a non existing book")
    @Test
    public void removeNonExistingBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try{
            library.addBook(book);
            library.removeBook("B2").equals("Book Removed");
            fail("A non existing book was removed from the library successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Add copies to an existing book")
    @Test
    public void addCopiesToExistingBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.addCopies(book.getIsbn(), 3);
            assertTrue(4 == book.getCopies());
        }
        catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Add copies to non a existing book")
    @Test
    public void addCopiesToNonExistingBook() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.addCopies("B2", 3);
            fail("Copies added to a non existing book successfully");
        }
        catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Search for an existing book with the title")
    @Test
    public void searchExistingBookByTitle() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            Book foundBook = library.searchBookByTitle(book.getTitle());
            assertTrue(foundBook instanceof Book);
            assertTrue(foundBook.getIsbn().equals(book.getIsbn()));
        }
        catch (Exception e){
            fail("An Exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Search for a non existing book with the title")
    @Test
    public void searchNonExistingBookByTitle() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.searchBookByTitle("Harry Potter and the Goblet of Fire");
            fail("Search for non existing book was successful");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Search for an existing book with the isbn")
    @Test
    public void searchExistingBookByIsbn() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            Book foundBook = library.searchBookByIsbn(book.getIsbn());
            assertTrue(foundBook instanceof Book);
            assertTrue(foundBook.getIsbn().equals(book.getIsbn()));
        }
        catch (Exception e){
            fail("An Exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Search for a non existing book with the isbn")
    @Test
    public void searchNoneExistingBookByIsbn() {
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.searchBookByIsbn("B2");
            fail("Search for non existing book was successful");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Search for an existing member")
    @Test
    public void searchExistingMember() {

    }

    @DisplayName("Search for a non existing member")
    @Test
    public void searchNonExistingMember() {

    }

    @DisplayName("Existing member borrows an existing book")
    @Test
    public void existingMemberBorrowExistingBook() {

    }

    @DisplayName("Non existing member borrows an existing book")
    @Test
    public void nonExistingMemberBorrowExistingBook() {

    }

    @DisplayName("Existing member borrows a non existing book")
    @Test
    public void existingMemberBorrowNonExistingBook() {

    }

    @DisplayName("Member borrows a book out of copies")
    @Test
    public void memberBorrowOutOfCopiesBook() {

    }

    @DisplayName("Member borrows a book while reaching the limit of books")
    @Test
    public void memberBorrowBookWithLimitExceeded() {

    }

    @DisplayName("Member borrows a book while have overdue books")
    @Test
    public void memberBorrowBookWithOverdueBooks() {

    }

    @DisplayName("Existing member borrows an existing book with a return date")
    @Test
    public void existingMemberBorrowExistingBookWithReturnDate() {

    }

    @DisplayName("Non existing member borrows an existing book with a return date")
    @Test
    public void nonExistingMemberBorrowExistingBookWithReturnDate() {

    }

    @DisplayName("Existing member borrows a non existing book with a return date")
    @Test
    public void existingMemberBorrowNonExistingBookWithReturnDate() {

    }

    @DisplayName("Member borrows a book out of copies with a return date")
    @Test
    public void memberBorrowOutOfCopiesBookWithReturnDate() {

    }

    @DisplayName("Member borrows a book with an expired return date")
    @Test
    public void memberBorrowBookWithExpiredDate() {

    }

    @DisplayName("Member borrows a book while reaching the limit of books with a return date")
    @Test
    public void memberBorrowBookWithLimitExceededWithReturnDate() {

    }

    @DisplayName("Member borrows a book while have overdue books with a return date")
    @Test
    public void memberBorrowBookWithOverdueBooksWithReturnDate() {

    }

    @DisplayName("Existing member returns a borrowed book")
    @Test
    public void existingMemberReturnBorrowedBook() {

    }

    @DisplayName("Non existing member returns a borrowed book")
    @Test
    public void nonExistingMemberReturnBorrowedBook() {

    }

    @DisplayName("Existing member returns a non existing book")
    @Test
    public void existingMemberReturnsNonExistingBook() {

    }

    @DisplayName("Existing member returns an existing book not borrowed by him")
    @Test
    public void existingMemberReturnsNonBorrowedBook() {

    }

    @DisplayName("Existing member returns all borrowed books")
    @Test
    public void existingMemberReturnAllBooks() {

    }

    @DisplayName("Non existing member returns all borrowed books")
    @Test
    public void nonExistingMemberReturnAllBooks() {

    }

    @DisplayName("Existing member returns all borrowed books with one or more non existing books")
    @Test
    public void existingMemberReturnsAllBooksWithNonExistingBook() {

    }
}
