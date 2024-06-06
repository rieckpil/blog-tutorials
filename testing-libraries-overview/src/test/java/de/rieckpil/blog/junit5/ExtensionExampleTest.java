package de.rieckpil.blog.junit5;

import static de.rieckpil.blog.junit5.RandomUUIDParameterResolver.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomUUIDParameterResolver.class)
public class ExtensionExampleTest {

  @RepeatedTest(5)
  public void testUUIDInjection(@RandomUUID String uuid) {
    System.out.println("Injected random UUID: " + uuid);
  }
}
