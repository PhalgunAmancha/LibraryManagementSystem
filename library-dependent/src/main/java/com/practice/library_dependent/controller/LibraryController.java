package com.practice.library_dependent.controller;

import com.practice.library_dependent.entity.Book;
import com.practice.library_dependent.entity.Library;
import com.practice.library_dependent.entity.User;
import com.practice.library_dependent.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer bookId){
        return libraryService.getBookById(bookId);
    }

    @PostMapping("books/add")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }

    @PutMapping("books/update/{bookId}")
    public ResponseEntity<Book> updateBookById(@PathVariable Integer bookId,@RequestBody Book updatedBook){
        return libraryService.updateBookById(bookId, updatedBook);
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer bookId){
        return libraryService.deleteBookById(bookId);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        return libraryService.getAllUsers();
    }

    @GetMapping("users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return libraryService.getUserByUsername(username);
    }

    @PostMapping("users/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return libraryService.addUser(user);
    }

    @PutMapping("users/update/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User updatedUser){
        return libraryService.updateUserByUsername(username, updatedUser);
    }

    @DeleteMapping("users/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username){
        return libraryService.deleteUserByUsername(username);
    }

    @PostMapping("users/{username}/books/{bookId}")
    public ResponseEntity<Library> issueBookToUser(@PathVariable("username") String username, @PathVariable("bookId") Integer bookId){
        return libraryService.issueBookToUser(username, bookId);
    }

    @DeleteMapping("users/{username}/books/{bookId}")
    public ResponseEntity<Void> releaseBookFromUser(@PathVariable("username") String username,@PathVariable("bookId") Integer bookId){
        return libraryService.releaseBookFromUser(username, bookId);
    }
}
