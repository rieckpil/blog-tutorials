package de.rieckpil.blog;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NotificationRequest {

  @Email
  private String email;

  @NotBlank
  private String content;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
