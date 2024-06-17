package de.rieckpil.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

  @SneakyThrows
  public String convert(@NonNull Object object) {
    return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
  }
}
