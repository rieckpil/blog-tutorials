package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CarDetailsJsonTest {

  @Autowired
  private JacksonTester<CarDetails> json;

  @Test
  public void testSerialize() throws Exception {
    CarDetails carDetails = new CarDetails("Audi", "A3", "gray");
    JsonContent<CarDetails> result = this.json.write(carDetails);
    System.out.println(result);
    assertThat(result).extractingJsonPathStringValue("$.type").contains("Audi", "A3", "gray");
  }
}
