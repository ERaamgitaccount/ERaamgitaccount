package com.example.Online_Book.RepositoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Online_Book.EntityLayer.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
	Author getAuthorById(Long authorId);
}
