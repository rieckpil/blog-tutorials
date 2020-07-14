package de.rieckpil.blog;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data // Lombok annotation to generate constructor/getter/setter...
@Entity
public class FileUpload {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fileName;

  private String fileType;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] fileContent;

  @CreationTimestamp // Hibernate annotation
  private LocalDateTime uploadedAt;
}
