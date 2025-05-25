package com.practice.library_management_system.service;

import com.practice.library_management_system.dao.BookDao;
import com.practice.library_management_system.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookById(Integer id) {
        return new ResponseEntity<>(bookDao.findById(id).get(),HttpStatus.OK);
    }

    public ResponseEntity<String> addBook(Book book) {
        bookDao.save(book);
        return new ResponseEntity<>("Book added Successfully",HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteBookById(Integer id) {
        bookDao.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

    public ResponseEntity<Book> updateBookById(Integer id,Book updatedBook) {
        return bookDao.findById(id).map(book ->{
            book.setName(updatedBook.getName());
            book.setPublisher(updatedBook.getPublisher());
            book.setAuthor(updatedBook.getAuthor());

            Book savedBook = bookDao.save(book);
            return new ResponseEntity<>(savedBook, HttpStatus.ACCEPTED);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
