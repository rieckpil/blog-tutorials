package de.rieckpil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Getter
@Entity
@Table(name = "hogwarts_houses")
public class HogwartsHouse {

  @Id
  @Column(name = "id", nullable = false, unique = true)
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;
}
