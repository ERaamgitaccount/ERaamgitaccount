package com.example.Online_Book.ControllerLayer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_Book.EntityLayer.Author;
import com.example.Online_Book.EntityLayer.Book;
import com.example.Online_Book.EntityLayer.Reviews;
import com.example.Online_Book.RepositoryLayer.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    // 1. GET /api/books
    @ExceptionHandler(NotFoundException.class)
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) Author author) {
        List<Book> books = bookRepo.getAllBooks(title, author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // 2. POST /api/books
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookRepo.addBook(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    // 3. GET /api/books/{isbn}
    @ExceptionHandler(NotFoundException.class)
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable Long isbn) {
        Book book = bookRepo.getBookByIsbn(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // 4. PUT /api/books/{isbn}
    @PutMapping("/{isbn}")
    public ResponseEntity<String> updateBookByIsbn(@PathVariable Long isbn, @RequestBody Book updatedBook) {
        bookRepo.updateBookByIsbn(isbn, updatedBook);
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    // 5. DELETE /api/books/{isbn}
    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteBookByIsbn(@PathVariable Long isbn) {
        bookRepo.deleteBookByIsbn(isbn);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    // 6. POST /api/books/{isbn}/reviews
    @PostMapping("/{isbn}/reviews")
    public ResponseEntity<String> submitReview(@PathVariable Long isbn, @RequestBody Reviews review) {
        bookRepo.submitReview(isbn, review);
        return new ResponseEntity<>("Review submitted successfully", HttpStatus.CREATED);
    }
}




