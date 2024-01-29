package de.rieckpil.blog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {

  @NotEmpty private String title;

  @NotEmpty
  @Size(max = 20)
  private String isbn;

  @NotEmpty private String author;
}
