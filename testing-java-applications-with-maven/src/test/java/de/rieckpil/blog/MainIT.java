package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainIT {

  private Main cut;

  @BeforeEach
  void setUp() {
    this.cut = new Main();
  }

  @Test
  void firstTest() {
    // insert meaningful test here

    System.out.println("Executing an integration test");
  }
}
