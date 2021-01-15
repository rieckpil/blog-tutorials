package de.rieckpil.blog;

import com.myorg.AwsCdkJavaIntroductionStack;
import software.amazon.awscdk.core.App;

public class MyCdkApp {

  public static void main(String[] args) {
    App app = new App();

    new AwsCdkJavaIntroductionStack(app, "AwsCdkJavaIntroductionStack");

    app.synth();
  }

}
