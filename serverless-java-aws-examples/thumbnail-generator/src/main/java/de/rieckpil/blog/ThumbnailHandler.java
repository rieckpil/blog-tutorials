package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ThumbnailHandler implements RequestHandler<S3Event, Void> {

  private static final Integer THUMBNAIL_SIZE = Integer.valueOf(System.getenv("THUMBNAIL_SIZE"));
  private static final String THUMBNAIL_PREFIX = "thumbnails/" + THUMBNAIL_SIZE + "x" + THUMBNAIL_SIZE + "-";

  private static final String AWS_REGION = System.getenv("AWS_REGION");

  @Override
  public Void handleRequest(S3Event s3Event, Context context) {
    String bucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
    String key = s3Event.getRecords().get(0).getS3().getObject().getKey();

    LambdaLogger logger = context.getLogger();
    logger.log("Going to create a thumbnail for: " + bucket + "/" + key);

    try(S3Client s3Client = S3Client.builder().region(Region.of(AWS_REGION)).build();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      logger.log("Connection to S3 established");

      ResponseBytes<GetObjectResponse> s3Response = s3Client.getObjectAsBytes(GetObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .build());

      logger.log("Successfully read uploaded S3 file");

      BufferedImage img = new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, BufferedImage.TYPE_INT_RGB);

      img
        .createGraphics()
        .drawImage(ImageIO.read(s3Response.asInputStream())
          .getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH), 0, 0, null);

      ImageIO.write(img, "png", outputStream);

      logger.log("Successfully created resized image");

      String targetKey = THUMBNAIL_PREFIX + key.replace("uploads/", "");

      s3Client.putObject(PutObjectRequest.builder()
        .bucket(bucket)
        .key(targetKey)
        .build(), RequestBody.fromBytes(outputStream.toByteArray()));

      logger.log("Successfully uploaded resized image with key " + targetKey);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
