package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.StackProps;

import java.util.Map;

public class AwsCdkJavaIntroductionApp {
  public static void main(final String[] args) {
    App app = new App();

    // new AwsCdkJavaIntroductionStack(app, "AwsCdkJavaIntroductionStack");
    new AwsCdkJavaIntroductionStack(app, "AwsCdkJavaIntroductionStack", StackProps.builder()
      .stackName("MyCustomStack")
      .tags(Map.of("env", "prod"))
      .build());

    app.synth();
  }
}
