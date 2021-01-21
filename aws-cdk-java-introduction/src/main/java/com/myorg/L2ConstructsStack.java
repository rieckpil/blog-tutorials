package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.*;

public class L2ConstructsStack extends Stack {
  public L2ConstructsStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    new Bucket(this, "L2Construct", BucketProps.builder()
      .bucketName("l2-example-12345-duke")
      .versioned(true)
      .blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
      .encryption(BucketEncryption.KMS)
      .removalPolicy(RemovalPolicy.DESTROY)
      .build());

  }
}
