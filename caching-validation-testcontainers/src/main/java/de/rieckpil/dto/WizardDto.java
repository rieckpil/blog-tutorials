package de.rieckpil.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WizardDto {

  private UUID id;
  private String name;
  private String house;
  private String wandType;
}
