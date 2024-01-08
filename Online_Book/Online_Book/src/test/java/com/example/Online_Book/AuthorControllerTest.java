package com.example.Online_Book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Online_Book.ControllerLayer.AuthorController;
import com.example.Online_Book.EntityLayer.Author;
import com.example.Online_Book.RepositoryLayer.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorControllerTest {

    @Mock
    private AuthorRepository authorRepo;

    @InjectMocks
    private AuthorController authorController;

    // Test case for successful retrieval of author by ID
    @Test
    public void testGetAuthorById_Success() {
        // Arrange
        Long authorId = 1L;
        Author expectedAuthor = new Author(authorId, "John Doe");
        when(authorRepo.getAuthorById(authorId)).thenReturn(expectedAuthor);

        // Act
        ResponseEntity<Author> responseEntity = authorController.getAuthorById(authorId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedAuthor, responseEntity.getBody());

        // Verify that the repository method was called with the correct parameter
        verify(authorRepo, times(1)).getAuthorById(authorId);
    }

    // Test case for handling exceptions (e.g., author not found)
    @Test
    public void testGetAuthorById_Exception() {
        // Arrange
        Long authorId = 2L;
        when(authorRepo.getAuthorById(authorId)).thenReturn(null);

        // Act
        ResponseEntity<Author> responseEntity = authorController.getAuthorById(authorId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verify that the repository method was called with the correct parameter
        verify(authorRepo, times(1)).getAuthorById(authorId);
    }
}
