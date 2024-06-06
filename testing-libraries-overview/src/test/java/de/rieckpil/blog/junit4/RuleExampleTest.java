package de.rieckpil.blog.junit4;

import java.io.File;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RuleExampleTest {

  @Rule public final TemporaryFolder tmpFolder = new TemporaryFolder();

  @Test
  public void testFileHandling() throws IOException {

    File catPicture = tmpFolder.newFile("cat.png");

    // fill the file with actual content ...

    // new ThumbnailGenerator().createProfilePicture(catPicture);
  }
}
