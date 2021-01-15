package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketAccessControl;
import software.amazon.awscdk.services.s3.BucketProps;

public class AwsCdkJavaIntroductionStack extends Stack {
  public AwsCdkJavaIntroductionStack(final Construct scope, final String id) {
    this(scope, id, null);
  }

  public AwsCdkJavaIntroductionStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    new Bucket(this, "MyBucket", BucketProps.builder()
      .bucketName("test-123-42-duke")
      .removalPolicy(RemovalPolicy.DESTROY)
      .accessControl(BucketAccessControl.PRIVATE)
      .versioned(true)
      .build());
  }
}
