package de.rieckpil.blog;

import java.io.IOException;
import java.time.Duration;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.cloud.pubsub.v1.stub.GrpcSubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStubSettings;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PullRequest;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.TopicName;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PubSubEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Testcontainers
class PubSubExampleTest {

  @Container
  static PubSubEmulatorContainer emulator =
    new PubSubEmulatorContainer(
      DockerImageName.parse("gcr.io/google.com/cloudsdktool/cloud-sdk:372.0.0-emulators"));

  static final String PROJECT_ID = "test-project";
  static final String TOPIC_ID = "sample-topic";
  static final String SUBSCRIPTION_ID = "sample-subscription";

  @BeforeAll
  static void setupEmulator() throws Exception {
    ManagedChannel channel = ManagedChannelBuilder.forTarget(emulator.getEmulatorEndpoint()).usePlaintext().build();
    TransportChannelProvider channelProvider =
      FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
    NoCredentialsProvider credentialsProvider = NoCredentialsProvider.create();

    createTopic(TOPIC_ID, channelProvider, credentialsProvider);
    createSubscription(SUBSCRIPTION_ID, TOPIC_ID, channelProvider, credentialsProvider);

  }

  @Test
  void shouldPublishAndConsumeMessage() throws Exception {

    Publisher publisher =
      Publisher.newBuilder(TopicName.of(PROJECT_ID, TOPIC_ID))
        .setChannelProvider(FixedTransportChannelProvider.create(GrpcTransportChannel.create(ManagedChannelBuilder.forTarget(emulator.getEmulatorEndpoint()).usePlaintext().build())))
        .setCredentialsProvider(NoCredentialsProvider.create())
        .build();

    publisher
      .publish(PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8("Hello World!"))
        .build());

    SubscriberStubSettings subscriberStubSettings =
      SubscriberStubSettings.newBuilder()
        .setTransportChannelProvider(FixedTransportChannelProvider.create(GrpcTransportChannel.create(ManagedChannelBuilder.forTarget(emulator.getEmulatorEndpoint()).usePlaintext().build())))
        .setCredentialsProvider(NoCredentialsProvider.create())
        .build();

    SubscriberStub subscriber = GrpcSubscriberStub
      .create(subscriberStubSettings);

    PullRequest pullRequest =
      PullRequest.newBuilder()
        .setMaxMessages(1)
        .setSubscription(ProjectSubscriptionName.format(PROJECT_ID, SUBSCRIPTION_ID))
        .build();

    await()
      .atMost(Duration.ofSeconds(3))
      .untilAsserted(() -> {
        PullResponse pullResponse = subscriber.pullCallable().call(pullRequest);

        assertThat(pullResponse.getReceivedMessagesList())
          .hasSize(1);

        assertThat(pullResponse.getReceivedMessagesList().get(0).getMessage().getData().toStringUtf8())
          .isEqualTo("Hello World!");
      });
  }

  private static void createTopic(
    String topicId,
    TransportChannelProvider channelProvider,
    NoCredentialsProvider credentialsProvider)
    throws IOException {
    TopicAdminSettings topicAdminSettings =
      TopicAdminSettings.newBuilder()
        .setTransportChannelProvider(channelProvider)
        .setCredentialsProvider(credentialsProvider)
        .build();
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create(topicAdminSettings)) {
      TopicName topicName = TopicName.of(PROJECT_ID, topicId);
      topicAdminClient.createTopic(topicName);
    } catch (AlreadyExistsException e) {
      // The topic already exists -- OK
    }
  }

  private static void createSubscription(
    String subscriptionId,
    String topicId,
    TransportChannelProvider channelProvider,
    NoCredentialsProvider credentialsProvider)
    throws IOException {
    SubscriptionAdminSettings subscriptionAdminSettings =
      SubscriptionAdminSettings.newBuilder()
        .setTransportChannelProvider(channelProvider)
        .setCredentialsProvider(credentialsProvider)
        .build();
    SubscriptionAdminClient subscriptionAdminClient =
      SubscriptionAdminClient.create(subscriptionAdminSettings);

    ProjectSubscriptionName subscriptionName =
      ProjectSubscriptionName.of(PROJECT_ID, subscriptionId);

    try {
      subscriptionAdminClient.createSubscription(
        subscriptionName, TopicName.of(PROJECT_ID, topicId), PushConfig.getDefaultInstance(), 10);
    } catch (AlreadyExistsException e) {
      // The subscription already exists -- OK
    }
  }

}
