package com.example.Online_Book.EntityLayer;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="book_details")
public class Book {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long isbn;
    @Column(name="title")
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviews_id")
    private Reviews reviews;
    
    public Book(Long isbn, String title, Author author, Reviews reviews) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.reviews = reviews;
    }
}
