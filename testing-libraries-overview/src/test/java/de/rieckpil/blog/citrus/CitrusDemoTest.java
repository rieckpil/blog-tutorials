package de.rieckpil.blog.citrus;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * First Citrus Test
 *
 * @author rieckpil
 * @since 2021-01-27
 */
@ExtendWith(CitrusExtension.class)
public class CitrusDemoTest {

  @Test
  @CitrusTest
  public void citrusDemo(@CitrusResource TestRunner testRunner) {
    testRunner.variable("time", "citrus:currentDate()");
    testRunner.echo("Hello Citrus!");
    testRunner.echo("CurrentTime is: ${time}");
    testRunner.echo("TODO: Code the test CitrusDemo");
  }
}
