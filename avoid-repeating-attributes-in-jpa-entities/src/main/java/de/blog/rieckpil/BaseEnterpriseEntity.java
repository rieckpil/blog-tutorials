package de.blog.rieckpil;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Data
@MappedSuperclass
public class BaseEnterpriseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String internId;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    this.internId = String.valueOf(Math.abs(ThreadLocalRandom.current().nextInt()));
  }

}
