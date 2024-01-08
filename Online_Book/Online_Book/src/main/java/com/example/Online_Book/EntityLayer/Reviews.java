package com.example.Online_Book.EntityLayer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="review_details")
public class Reviews {
	
	@Id
    @GeneratedValue( strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Long reviewId;
	@Column(name="user_name")
	String username;
	@Column(name="comment")
	String comment;
	
	public Reviews(String username, String comment) {
       this.username=username;
       this.comment=comment;
    }

}
