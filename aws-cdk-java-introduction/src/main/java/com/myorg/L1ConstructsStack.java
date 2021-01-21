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

    CfnBucket s3Bucket = new CfnBucket(this, "L1Bucket", CfnBucketProps.builder()
      .bucketName("l1-test-bucket-123")
      .versioningConfiguration(CfnBucket.VersioningConfigurationProperty.builder().status("Enabled").build())
      .accessControl("public-read")
      .publicAccessBlockConfiguration(CfnBucket.PublicAccessBlockConfigurationProperty.builder()
        .blockPublicAcls(true)
        .blockPublicPolicy(true)
        .ignorePublicAcls(true)
        .restrictPublicBuckets(true)
        .build())
      .build());

    s3Bucket.applyRemovalPolicy(RemovalPolicy.RETAIN);
  }
}
