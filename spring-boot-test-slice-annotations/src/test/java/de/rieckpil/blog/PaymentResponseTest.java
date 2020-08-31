package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonTest
class PaymentResponseTest {

  @Autowired
  private JacksonTester<PaymentResponse> jacksonTester;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void shouldSerializeObject() throws IOException {

    assertNotNull(objectMapper);

    PaymentResponse paymentResponse = new PaymentResponse();
    paymentResponse.setId("42");
    paymentResponse.setAmount(new BigDecimal("42.50"));
    paymentResponse.setPaymentConfirmationCode(UUID.randomUUID());
    paymentResponse.setPaymentTime(LocalDateTime.parse("2020-07-20T19:00:00.123"));

    JsonContent<PaymentResponse> result = jacksonTester.write(paymentResponse);

    assertThat(result).hasJsonPathStringValue("$.paymentConfirmationCode");
    assertThat(result).extractingJsonPathNumberValue("$.payment_amount").isEqualTo(42.50);
    assertThat(result).extractingJsonPathStringValue("$.paymentTime").isEqualTo("2020-07-20|19:00:00");
    assertThat(result).doesNotHaveJsonPath("$.id");
  }
}
