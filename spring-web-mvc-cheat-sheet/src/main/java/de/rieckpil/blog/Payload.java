package de.rieckpil.blog;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Payload {

  @NotBlank private String message;

  @Email private String email;

  @Future private LocalDateTime memberSince;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getMemberSince() {
    return memberSince;
  }

  public void setMemberSince(LocalDateTime memberSince) {
    this.memberSince = memberSince;
  }
}
