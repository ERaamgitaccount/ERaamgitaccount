package com.example.Online_Book;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.example.Online_Book.ControllerLayer.BookController;
import com.example.Online_Book.EntityLayer.Author;
import com.example.Online_Book.EntityLayer.Book;
import com.example.Online_Book.EntityLayer.Reviews;
import com.example.Online_Book.RepositoryLayer.BookRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRepository bookRepo;

    @Test
    public void testGetAllBooks() {
        // Mock data
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1L, "Title1", new Author(1L,"Name1"),new Reviews ("Name1","Comment")));
        mockBooks.add(new Book(2L, "Title2", new Author(2L,"Name2"),new Reviews ("Name1","Comment")));

        // Mocking repository method
        Mockito.when(bookRepo.getAllBooks(any(), any())).thenReturn(mockBooks);

        // Call the API endpoint
        ResponseEntity<List<Book>> response = bookController.getAllBooks(null, null);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    public void testAddBook() {
        // Mock data
        Book newBook = new Book(1L, "Title1", new Author(1L,"Name1"),new Reviews ("Name1","Comment"));

        // Call the API endpoint
        ResponseEntity<String> response = bookController.addBook(newBook);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Book added successfully", response.getBody());
        // Verify that the repository method was called with the correct parameters
        Mockito.verify(bookRepo).addBook(newBook);
    }

    @Test
    public void testGetBookByIsbn() {
        // Mock data
        Long isbn = 1L;
        Book mockBook = new Book(isbn, "Title1",new Author(1L,"Name1"),new Reviews ("Name1","Comment"));

        // Mocking repository method
        Mockito.when(bookRepo.getBookByIsbn(isbn)).thenReturn(mockBook);

        // Call the API endpoint
        ResponseEntity<Book> response = bookController.getBookByIsbn(isbn);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    public void testUpdateBookByIsbn() {
        // Mock data
        Long isbn = 1L;
        Book updatedBook = new Book(isbn, "UpdatedTitle",new Author(1L,"Name1"),new Reviews ("Name1","Comment"));

        // Call the API endpoint
        ResponseEntity<String> response = bookController.updateBookByIsbn(isbn, updatedBook);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book updated successfully", response.getBody());
        // Verify that the repository method was called with the correct parameters
        Mockito.verify(bookRepo).updateBookByIsbn(eq(isbn), eq(updatedBook));
    }

    @Test
    public void testDeleteBookByIsbn() {
        // Mock data
        Long isbn = 1L;

        // Call the API endpoint
        ResponseEntity<String> response = bookController.deleteBookByIsbn(isbn);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted successfully", response.getBody());
        // Verify that the repository method was called with the correct parameters
        Mockito.verify(bookRepo).deleteBookByIsbn(isbn);
    }

    @Test
    public void testSubmitReview() {
        // Mock data
        Long isbn = 1L;
        Reviews review = new Reviews("Great book!", "Ram");

        // Call the API endpoint
        ResponseEntity<String> response = bookController.submitReview(isbn, review);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Review submitted successfully", response.getBody());
        // Verify that the repository method was called with the correct parameters
        Mockito.verify(bookRepo).submitReview(eq(isbn), eq(review));
    }
}
