package de.rieckpil.blog;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {

  @JsonIgnore
  private String id;

  private UUID paymentConfirmationCode;

  @JsonProperty("payment_amount")
  private BigDecimal amount;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd|HH:mm:ss", locale = "en_US")
  private LocalDateTime paymentTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UUID getPaymentConfirmationCode() {
    return paymentConfirmationCode;
  }

  public void setPaymentConfirmationCode(UUID paymentConfirmationCode) {
    this.paymentConfirmationCode = paymentConfirmationCode;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(LocalDateTime paymentTime) {
    this.paymentTime = paymentTime;
  }
}
