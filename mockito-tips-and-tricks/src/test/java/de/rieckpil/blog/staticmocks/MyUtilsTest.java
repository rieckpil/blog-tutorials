package de.rieckpil.blog.staticmocks;

import de.rieckpil.blog.staticsmocks.MyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;

class MyUtilsTest {

  @Test
  void shouldMockStaticMethod() {

    try (MockedStatic<MyUtils> mockedStatic = Mockito.mockStatic(MyUtils.class)) {

      mockedStatic
        .when(() -> MyUtils.getWelcomeMessage(eq("duke"), anyBoolean()))
        .thenReturn("Howdy duke!");

      String result = MyUtils.getWelcomeMessage("duke", false);

      assertEquals("Howdy duke!", result);
    }
  }
}
