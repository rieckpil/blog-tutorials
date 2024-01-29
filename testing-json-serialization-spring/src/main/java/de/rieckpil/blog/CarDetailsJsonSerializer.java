package de.rieckpil.blog;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CarDetailsJsonSerializer extends JsonSerializer<CarDetails> {

  @Override
  public void serialize(
      CarDetails value, JsonGenerator jsonGenerator, SerializerProvider serializers)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField(
        "type", value.getManufacturer() + "|" + value.getType() + "|" + value.getColor());
    jsonGenerator.writeEndObject();
  }
}
