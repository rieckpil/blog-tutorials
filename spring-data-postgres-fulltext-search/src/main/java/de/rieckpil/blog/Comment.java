package de.rieckpil.blog;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "tsvector", typeClass = PostgreSQLTSVectorType.class, defaultForType = String.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(columnDefinition = "tsvector")
	private String commentText;

	private Instant createdAt;

	public Comment() {

	}

	public Comment(Long id, String commentText, Instant createdAt) {
		this.id = id;
		this.commentText = commentText;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}
