package de.blog.rieckpil;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@MappedSuperclass
public class BaseEnterpriseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String internId;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    this.internId = String.valueOf(Math.abs(ThreadLocalRandom.current().nextInt()));
  }
}
