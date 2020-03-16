package de.rieckpil.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class UserDetails {

  private Long id;
  private String firstName;
  private String lastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dateOfBirth;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private boolean enabled;

  public UserDetails(Long id, String firstName, String lastName, LocalDate dateOfBirth, boolean enabled) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.enabled = enabled;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
