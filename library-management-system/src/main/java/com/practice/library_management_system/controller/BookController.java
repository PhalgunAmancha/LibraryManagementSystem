package com.practice.library_management_system.controller;

import com.practice.library_management_system.entity.Book;
import com.practice.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }

    @PostMapping("books/add")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @DeleteMapping("books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id){
        return bookService.deleteBookById(id);
    }

    @PutMapping("books/update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Integer id, @RequestBody Book updatedBook){
        return bookService.updateBookById(id,updatedBook);
    }
}
