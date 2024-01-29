package de.rieckpil.tutorials;

import com.querydsl.core.annotations.QueryEntity;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@QueryEntity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstname;

  private String lastname;

  private Instant dob;

  private Integer budget;
}
