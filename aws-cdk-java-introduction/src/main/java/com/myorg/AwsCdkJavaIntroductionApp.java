package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.StackProps;

public class AwsCdkJavaIntroductionApp {
  public static void main(final String[] args) {
    App app = new App();

    new L1ConstructsStack(app, "L1ConstructsStack", StackProps.builder()
      .stackName("L1Example")
      .build());

    new L2ConstructsStack(app, "L2ConstructsStack", StackProps.builder()
      .stackName("L2Example")
      .build());

    new L3ConstructsStack(app, "L3ConstructsStack", StackProps.builder()
      .stackName("L3Example")
      .build());

    app.synth();
  }
}
