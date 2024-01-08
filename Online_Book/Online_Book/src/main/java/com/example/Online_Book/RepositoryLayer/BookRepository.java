package com.example.Online_Book.RepositoryLayer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Online_Book.EntityLayer.Book;
import com.example.Online_Book.EntityLayer.Reviews;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

	List<Book> getAllBooks(Object object,Object object2);
	
	String addBook(Book book);
	
	Book getBookByIsbn(Long isbn);
	
	String updateBookByIsbn(Long isbn, Book updatedBook);
	
	String deleteBookByIsbn(Long isbn);
	
	String submitReview(Long isbn,Reviews review);
}
