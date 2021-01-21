package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.CfnBucket;
import software.amazon.awscdk.services.s3.CfnBucketProps;

public class L1ConstructsStack extends Stack {

  public L1ConstructsStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    CfnBucket bucket = new CfnBucket(this, "L1Construct", CfnBucketProps.builder()
      .bucketName("example-l1-12345")
      .versioningConfiguration(CfnBucket.VersioningConfigurationProperty.builder()
        .status("Enabled").build())
      .publicAccessBlockConfiguration(CfnBucket.PublicAccessBlockConfigurationProperty.builder()
        .restrictPublicBuckets(true)
        .ignorePublicAcls(true)
        .blockPublicAcls(true)
        .blockPublicPolicy(true)
        .build())
      .build());

    bucket.applyRemovalPolicy(RemovalPolicy.RETAIN);
  }
}
