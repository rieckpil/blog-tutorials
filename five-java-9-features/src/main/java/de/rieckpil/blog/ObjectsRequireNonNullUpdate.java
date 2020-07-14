package de.rieckpil.blog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class ObjectsRequireNonNullUpdate {

  public static void main(String[] args) {
    transferMoney(null, null);
    transferMoney(null, new BigDecimal(33.33).setScale(2, RoundingMode.DOWN));
    transferMoney("Duke", null);
    transferMoney("Duke", new BigDecimal(1995));
  }

  public static void transferMoney(String recipient, BigDecimal amount) {
    amount = Objects.requireNonNullElseGet(amount, ObjectsRequireNonNullUpdate::calculateDefaultAmount);
    recipient = Objects.requireNonNullElse(recipient, "Phil");

    System.out.println(amount + " is transfered to " + recipient);
  }

  private static BigDecimal calculateDefaultAmount() {
    return new BigDecimal(ThreadLocalRandom.current().nextBoolean() ? 1337 : 42);
  }
}
