package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

import lombok.Getter;
import lombok.Setter;
import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class FieldsIgnoreTest {

  @Test
  void shouldCreateUserForValidRequest() {
    var request =
        Instancio.of(UserCreationRequest.class)
            .ignore(field(UserCreationRequest::getMiddleName))
            .ignore(field("referralCode"))
            .create();

    assertThat(request.getFirstName()).isNotBlank();
    assertThat(request.getLastName()).isNotBlank();
    assertThat(request.getEmail()).isNotBlank();
    assertThat(request.getPassword()).isNotBlank();

    assertThat(request.getMiddleName()).isNull();
    assertThat(request.getReferralCode()).isNull();
  }

  @RepeatedTest(value = 10)
  void shouldCreateUserHandlingOptionalUserFields() {
    var request =
        Instancio.of(UserCreationRequest.class)
            .withNullable(field(UserCreationRequest::getMiddleName))
            .withNullable(field(UserCreationRequest::getReferralCode))
            .create();

    assertThat(request.getFirstName()).isNotBlank();
    assertThat(request.getLastName()).isNotBlank();
    assertThat(request.getEmail()).isNotBlank();
    assertThat(request.getPassword()).isNotBlank();

    assertThat(request.getMiddleName())
        .satisfiesAnyOf(
            value -> assertThat(value).isNull(), value -> assertThat(value).isNotNull());
    assertThat(request.getMiddleName())
        .satisfiesAnyOf(
            value -> assertThat(value).isNull(), value -> assertThat(value).isNotNull());
  }

  @Getter
  @Setter
  static class UserCreationRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String referralCode;
  }
}
