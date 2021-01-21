package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.*;

public class L2ConstructsStack extends Stack {
  public L2ConstructsStack(final Construct scope, final String id) {
    this(scope, id, null);
  }

  public L2ConstructsStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    Bucket s3Bucket = new Bucket(this, "MyBucket", BucketProps.builder()
      .bucketName("test-123-42-duke")
      .encryption(BucketEncryption.KMS)
      // .removalPolicy(RemovalPolicy.DESTROY) // show first with default
      .accessControl(BucketAccessControl.PRIVATE)
      .blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
      .versioned(true)
      .build());

  }
}
