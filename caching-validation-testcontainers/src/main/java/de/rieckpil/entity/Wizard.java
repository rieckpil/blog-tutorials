package de.rieckpil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wizards")
public class Wizard {

  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "house_id", nullable = false)
  private UUID houseId;

  @Setter(AccessLevel.NONE)
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "house_id", nullable = false, insertable = false, updatable = false)
  private HogwartsHouse house;

  @Column(name = "wand_type")
  private String wandType;

  @Setter(AccessLevel.NONE)
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
