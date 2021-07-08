package de.rieckpil.blog.exercise9;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
class CustomerDtoTest {

  @Autowired
  private JacksonTester<CustomerDto> json;

  @Test
  void shouldSerializeWithCustomAttributeNamesAndDateFormat() throws Exception {
    CustomerDto customerDto = new CustomerDto(
      new Customer("Mike", ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneId.of("Europe/Berlin"))));

    JsonContent<CustomerDto> result = json.write(customerDto);

    assertThat(result).hasJsonPathStringValue("@.member_since");
    assertThat(result).hasJsonPathStringValue("@.customerName");
    assertThat(result).extractingJsonPathStringValue("@.member_since").isEqualTo("01-01 00:00");
  }
}
