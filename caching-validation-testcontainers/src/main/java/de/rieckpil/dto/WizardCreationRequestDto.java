package de.rieckpil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WizardCreationRequestDto {

  @Size(max = 50)
  @NotBlank(message = "Name must not be empty")
  private String name;

  @Pattern(
      regexp = "^(Dragon heartstring|Phoenix feather|Unicorn tail hair)$",
      message = "Invalid WandType")
  private String wandType;
}
