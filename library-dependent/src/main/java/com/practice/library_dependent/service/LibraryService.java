package com.practice.library_dependent.service;

import com.practice.library_dependent.clients.BookServiceClient;
import com.practice.library_dependent.clients.UserServiceClient;
import com.practice.library_dependent.dao.LibraryDao;
import com.practice.library_dependent.entity.Book;
import com.practice.library_dependent.entity.Library;
import com.practice.library_dependent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    BookServiceClient bookServiceClient;
    @Autowired
    UserServiceClient userServiceClient;

    @Autowired
    LibraryDao libraryDao;

    private static final int MAX_BOOKS_PER_USER = 3;
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookServiceClient.getAllBooks();
    }

    public ResponseEntity<Book> getBookById(Integer bookId) {
        return bookServiceClient.getBookById(bookId);
    }

    public ResponseEntity<String> addBook(Book book) {
        return bookServiceClient.addBook(book);
    }

    public ResponseEntity<Book> updateBookById(Integer bookId, Book updatedBook) {
        return bookServiceClient.updateBookById(bookId,updatedBook);
    }

    public ResponseEntity<Void> deleteBookById(Integer bookId) {
        return bookServiceClient.deleteBookById(bookId);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return userServiceClient.getAllUsers();
    }

    public ResponseEntity<User> getUserByUsername(String username) {
        return userServiceClient.getUserByUsername(username);
    }

    public ResponseEntity<String> addUser(User user) {
        return userServiceClient.addUser(user);
    }

    public ResponseEntity<User> updateUserByUsername(String username, User updatedUser) {
        return userServiceClient.updateUserByUsername(username,updatedUser);
    }

    public ResponseEntity<Void> deleteUserByUsername(String username) {
        return userServiceClient.deleteUserByUsername(username);
    }

    @Transactional
    public ResponseEntity<Library> issueBookToUser(String username, Integer bookId) {
        User user = userServiceClient.getUserByUsername(username).getBody();
        Book book = bookServiceClient.getBookById(bookId).getBody();
        if(libraryDao.findByUsernameAndBookId(username,bookId).isPresent()){
            throw new RuntimeException("Book is already issued to this user");
        }
        if(libraryDao.countByUsername(username) >= MAX_BOOKS_PER_USER){
            throw new RuntimeException("User has already reached the maximum limit of " + MAX_BOOKS_PER_USER + " books");
        }
        Library library = new Library();
        library.setUsername(username);
        library.setBookId(bookId);
        libraryDao.save(library);
        return new ResponseEntity<>(library, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Void> releaseBookFromUser(String username, Integer bookId) {
        Library library =  libraryDao.findByUsernameAndBookId(username,bookId).orElseThrow(() -> new RuntimeException("Book not issued to this User"));
        libraryDao.delete(library);
        return ResponseEntity.noContent().build();
    }
}
