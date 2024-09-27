package Test;

import Exceptions.*;
import Models.Book;
import Models.Library;
import Models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
                if(members.get(i).getName().equals(member.getName())) {
                    assertTrue(true, "Member added Successfully");
                    return;
                }
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
                if(book.getIsbn().equals(books.get(i).getIsbn())) {
                    assertTrue(true);
                    return;
                }
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
            assertTrue(e instanceof BookNotFoundException);
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
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            Person foundMember = library.searchMember(member.getName());
            assertTrue(foundMember instanceof Person);
            assertTrue(foundMember.getName().equals(member.getName()));
        }
        catch (Exception e){
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Search for a non existing member")
    @Test
    public void searchNonExistingMember() {
        Person member = new Person("Mark", 25);
        try {
            library.addMember(member);
            library.searchMember("Ahmed");
            fail("Search successfully for a non existing member");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Existing member borrows an existing book")
    @Test
    public void existingMemberBorrowExistingBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), member.getName());
            assertTrue(book.getCopies() == 0);
            ArrayList<Book> memberBooks = member.getBorrowedBooks();
            for(int i=0; i < memberBooks.size(); i++){
                if(memberBooks.get(i).getIsbn().equals(book.getIsbn())){
                    assertTrue(true);
                    return;
                }
            }
        }
        catch (Exception e){
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Non existing member borrows an existing book")
    @Test
    public void nonExistingMemberBorrowExistingBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), "Ahmed");
            fail("Book borrowed successfully for a non existing member");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Existing member borrows a non existing book")
    @Test
    public void existingMemberBorrowNonExistingBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook("B2", member.getName());
            fail("A non existing book borrowed successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Member borrows a book out of copies")
    @Test
    public void memberBorrowOutOfCopiesBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 0);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), member.getName());
            fail("An out of copies book borrowed successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof OutOfBookCopiesException);
        }
    }

    @DisplayName("Member borrows a book while reaching the limit of books")
    @Test
    public void memberBorrowBookWithLimitExceeded() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        Book book3 = new Book("B3", "How to Kill a Mockingbird", "Harper Lee", 345, 1);
        Book book4 = new Book("B4", "Who Moved my Cheese?", "Spencer Johnson", 164, 1);
        Book book5 = new Book("B5", "The Hunger Games", "Suzanne Collins", 464, 1);
        Book book6 = new Book("B6", "Mockingjay", "Suzanne Collins", 423, 1);
        try {
            library.addMember(member);
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.addBook(book5);
            library.addBook(book6);
            library.borrowBook(book1.getIsbn(), member.getName());
            library.borrowBook(book2.getIsbn(), member.getName());
            library.borrowBook(book3.getIsbn(), member.getName());
            library.borrowBook(book4.getIsbn(), member.getName());
            library.borrowBook(book5.getIsbn(), member.getName());
            library.borrowBook(book6.getIsbn(), member.getName());
            fail("Member borrowed more than 5 books successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof MaximumBorrowedBooksExceededException);
        }
    }

    @DisplayName("Member borrows a book while have overdue books")
    @Test
    public void memberBorrowBookWithOverdueBooks() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        try {
            library.addBook(book1);
            library.addBook(book2);
            library.addMember(member);
            library.borrowBook(book1.getIsbn(), member.getName());
            member.getBorrowedBooks().get(0).setReturnDate(LocalDate.now().minusWeeks(1));
            library.borrowBook(book2.getIsbn(), member.getName());
            fail("Member with overdue books successfully borrowed a book");
        }
        catch (Exception e) {
            assertTrue(e instanceof MemberOverdueBooksException);
        }
    }

    @DisplayName("Existing member borrows an existing book with a return date")
    @Test
    public void existingMemberBorrowExistingBookWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            assertTrue(book.getCopies() == 0);
            ArrayList<Book> memberBooks = member.getBorrowedBooks();
            for(int i=0; i < memberBooks.size(); i++){
                if(memberBooks.get(i).getIsbn().equals(book.getIsbn())){
                    assertTrue(true);
                    assertTrue(memberBooks.get(i).getReturnDate().equals(LocalDate.now().plusDays(2)));
                    return;
                }
            }
        }
        catch (Exception e){
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Non existing member borrows an existing book with a return date")
    @Test
    public void nonExistingMemberBorrowExistingBookWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), "Ahmed", LocalDate.now().plusDays(2));
            fail("Book borrowed successfully for a non existing member");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Existing member borrows a non existing book with a return date")
    @Test
    public void existingMemberBorrowNonExistingBookWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook("B2", member.getName(), LocalDate.now().plusDays(2));
            fail("A non existing book borrowed successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Member borrows a book out of copies with a return date")
    @Test
    public void memberBorrowOutOfCopiesBookWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 0);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            fail("An out of copies book borrowed successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof OutOfBookCopiesException);
        }
    }

    @DisplayName("Member borrows a book with an expired return date")
    @Test
    public void memberBorrowBookWithExpiredDate() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addMember(member);
            library.addBook(book);
            library.borrowBook(book.getIsbn(), member.getName(), LocalDate.now().minusWeeks(1));
            fail("A book was borrowed successfully with an expired date");
        }
        catch (Exception e){
            assertTrue(e instanceof ExpiredDateException);
        }
    }

    @DisplayName("Member borrows a book while reaching the limit of books with a return date")
    @Test
    public void memberBorrowBookWithLimitExceededWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        Book book3 = new Book("B3", "How to Kill a Mockingbird", "Harper Lee", 345, 1);
        Book book4 = new Book("B4", "Who Moved my Cheese?", "Spencer Johnson", 164, 1);
        Book book5 = new Book("B5", "The Hunger Games", "Suzanne Collins", 464, 1);
        Book book6 = new Book("B6", "Mockingjay", "Suzanne Collins", 423, 1);
        try {
            library.addMember(member);
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.addBook(book5);
            library.addBook(book6);
            library.borrowBook(book1.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            library.borrowBook(book2.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            library.borrowBook(book3.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            library.borrowBook(book4.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            library.borrowBook(book5.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            library.borrowBook(book6.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            fail("Member borrowed more than 5 books successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof MaximumBorrowedBooksExceededException);
        }
    }

    @DisplayName("Member borrows a book while have overdue books with a return date")
    @Test
    public void memberBorrowBookWithOverdueBooksWithReturnDate() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        try {
            library.addBook(book1);
            library.addBook(book2);
            library.addMember(member);
            library.borrowBook(book1.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            member.getBorrowedBooks().get(0).setReturnDate(LocalDate.now().minusWeeks(1));
            library.borrowBook(book2.getIsbn(), member.getName(), LocalDate.now().plusDays(2));
            fail("Member with overdue books successfully borrowed a book");
        }
        catch (Exception e) {
            assertTrue(e instanceof MemberOverdueBooksException);
        }
    }

    @DisplayName("Existing member returns a borrowed book")
    @Test
    public void existingMemberReturnBorrowedBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.addMember(member);
            library.borrowBook(book.getIsbn(), member.getName());
            library.returnBook(book.getIsbn(), member.getName());
            assertTrue(member.getBorrowedBooks().size() == 0);
            assertTrue(book.getCopies() == 1);
        }
        catch (Exception e){
            fail("Exception was thrown: " + e.getMessage());
        }
    }

    @DisplayName("Non existing member returns a borrowed book")
    @Test
    public void nonExistingMemberReturnBorrowedBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.addMember(member);
            library.borrowBook(book.getIsbn(), member.getName());
            library.returnBook(book.getIsbn(), "Ahmed");
            fail("Non existing member returned a book successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Existing member returns a non existing book")
    @Test
    public void existingMemberReturnsNonExistingBook() {
        Person member = new Person("Mark", 25);
        Book book = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        try {
            library.addBook(book);
            library.addMember(member);
            library.borrowBook(book.getIsbn(), member.getName());
            library.returnBook("B2", member.getName());
            fail("Non existing book returned successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }

    @DisplayName("Existing member returns an existing book not borrowed by him")
    @Test
    public void existingMemberReturnsNonBorrowedBook() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        try {
            library.addBook(book1);
            library.addBook(book2);
            library.addMember(member);
            library.borrowBook(book1.getIsbn(), member.getName());
            library.returnBook(book2.getIsbn(), member.getName());
            fail("Non existing book returned successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotBorrowedException);
        }
    }

    @DisplayName("Existing member returns all borrowed books")
    @Test
    public void existingMemberReturnAllBooks() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        Book book3 = new Book("B3", "How to Kill a Mockingbird", "Harper Lee", 345, 1);
        Book book4 = new Book("B4", "Who Moved my Cheese?", "Spencer Johnson", 164, 1);
        Book book5 = new Book("B5", "The Hunger Games", "Suzanne Collins", 464, 1);
        try {
            library.addMember(member);
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.addBook(book5);
            library.borrowBook(book1.getIsbn(), member.getName());
            library.borrowBook(book2.getIsbn(), member.getName());
            library.borrowBook(book3.getIsbn(), member.getName());
            library.borrowBook(book4.getIsbn(), member.getName());
            library.borrowBook(book5.getIsbn(), member.getName());
            library.returnAllBooks(member.getName());
            assertTrue(member.getBorrowedBooks().size() == 0);
            assertTrue(book1.getCopies() == 1);
            assertTrue(book2.getCopies() == 1);
            assertTrue(book3.getCopies() == 1);
            assertTrue(book4.getCopies() == 1);
            assertTrue(book5.getCopies() == 1);
        }
        catch (Exception e){
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @DisplayName("Non existing member returns all borrowed books")
    @Test
    public void nonExistingMemberReturnAllBooks() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        Book book3 = new Book("B3", "How to Kill a Mockingbird", "Harper Lee", 345, 1);
        Book book4 = new Book("B4", "Who Moved my Cheese?", "Spencer Johnson", 164, 1);
        Book book5 = new Book("B5", "The Hunger Games", "Suzanne Collins", 464, 1);
        try {
            library.addMember(member);
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.addBook(book5);
            library.borrowBook(book1.getIsbn(), member.getName());
            library.borrowBook(book2.getIsbn(), member.getName());
            library.borrowBook(book3.getIsbn(), member.getName());
            library.borrowBook(book4.getIsbn(), member.getName());
            library.borrowBook(book5.getIsbn(), member.getName());
            library.returnAllBooks("Ahmed");
            fail("Non existing member returned all books successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof MemberNotFoundException);
        }
    }

    @DisplayName("Existing member returns all borrowed books with one or more non existing books")
    @Test
    public void existingMemberReturnsAllBooksWithNonExistingBook() {
        Person member = new Person("Mark", 25);
        Book book1 = new Book("B1", "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 255, 1);
        Book book2 = new Book("B2", "Harry Potter and the Goblet of Fire", "J. K. Rowling", 234, 1);
        Book book3 = new Book("B3", "How to Kill a Mockingbird", "Harper Lee", 345, 1);
        Book book4 = new Book("B4", "Who Moved my Cheese?", "Spencer Johnson", 164, 1);
        Book book5 = new Book("B5", "The Hunger Games", "Suzanne Collins", 464, 1);
        try {
            library.addMember(member);
            library.addBook(book1);
            library.addBook(book2);
            library.addBook(book3);
            library.addBook(book4);
            library.borrowBook(book1.getIsbn(), member.getName());
            library.borrowBook(book2.getIsbn(), member.getName());
            library.borrowBook(book3.getIsbn(), member.getName());
            library.borrowBook(book4.getIsbn(), member.getName());
            member.getBorrowedBooks().add(book5);
            library.returnAllBooks(member.getName());
            fail("Non existing member returned all books successfully");
        }
        catch (Exception e){
            assertTrue(e instanceof BookNotFoundException);
        }
    }
}
