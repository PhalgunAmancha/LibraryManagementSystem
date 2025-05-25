package com.practice.library_dependent.clients;

import com.practice.library_dependent.entity.Book;
import org.apache.hc.core5.http.config.NamedElementChain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "LIBRARY-MANAGEMENT-SYSTEM")
public interface BookServiceClient {
    @GetMapping("api/books")
    public ResponseEntity<List<Book>>getAllBooks();

    @GetMapping("api/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Integer bookId);

    @PostMapping("api/books/add")
    public ResponseEntity<String> addBook(@RequestBody Book book);

    @PutMapping("api/books/update/{bookId}")
    public ResponseEntity<Book> updateBookById(@PathVariable("bookId") Integer bookId,@RequestBody Book updateBook);

    @DeleteMapping("api/books/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") Integer bookId);
}
