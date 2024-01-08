package com.example.Online_Book.ControllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_Book.EntityLayer.Author;
import com.example.Online_Book.RepositoryLayer.AuthorRepository;

//AuthorController.java
@ControllerAdvice
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

@Autowired
private AuthorRepository authorRepo;

// 7. GET /api/authors/{authorId}
@ExceptionHandler(Exception.class)
@GetMapping("/{authorId}")
public ResponseEntity<Author> getAuthorById(@PathVariable Long authorId) {
   Author author = authorRepo.getAuthorById(authorId);
   return new ResponseEntity<>(author, HttpStatus.OK);
}
}

