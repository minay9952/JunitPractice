This is a simple library management project designed to demonstrate good coding principles and the use of unit tests. The project consists of three models and nine exception classes.

The models are Person, Book, and Library. The Person model represents a member who can borrow books from the library. The Book model represents a book that can be borrowed from the library. The Library model consists of a group of members and a group of books.

Any member can borrow up to 5 books. However, if a member has one or more overdue books, they can't borrow any more books until they return or renew the overdue book(s). Furthermore, each book has a certain number of copies in the library. If the library is out of copies, the book can't be borrowed until copies are available again.

The exception classes created are:

BookExistException: Used when an already existing book in the library is being added to it again.
BookNotBorrowedException: Used when a member tries to return or renew a book they didn't borrow in the first place.
BookNotFoundException: Used when trying to search for, borrow, return, or renew a book that doesn't exist in the library.
ExpiredDateException: Used when a member tries to borrow a book and provides a return date that has already passed.
MaximumBooksBorrowedExceededException: Used when a member tries to borrow more than 5 books.
MemberExistException: Used when trying to add an existing member again to the library.
MemberNotFoundException: Used when search, borrowing, returning, or renewing actions are made on a member who doesn't exist in the library.
MemberOverdueBooksException: Used when a member tries to borrow a book while having one or more overdue books.
OutOfBookCopiesException: Used when trying to borrow a book while no more copies are available in the library.

Finally, there is the LibraryTest class, which holds 36 JUnit test cases that demonstrate unit testing principles.
