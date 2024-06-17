package de.rieckpil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {

  @Id
  @Setter(AccessLevel.NONE)
  private UUID id;

  private String title;

  private String writtenBy;

  @PrePersist
  void onCreate() {
    this.id = UUID.randomUUID();
  }
}
