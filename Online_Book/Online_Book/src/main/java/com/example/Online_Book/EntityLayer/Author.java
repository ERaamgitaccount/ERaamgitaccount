package com.example.Online_Book.EntityLayer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="author_details")
public class Author {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="id")
	  private Long authorId;
	  @Column(name="name")
	  private String name;
	 
	  
	  public Author(Long authorId, String name) {
	        this.authorId = authorId;
	        this.name = name;
	       
	    }
	
}
