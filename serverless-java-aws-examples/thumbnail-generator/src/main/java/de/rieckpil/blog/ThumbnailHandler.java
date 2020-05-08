package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ThumbnailHandler implements RequestHandler<S3Event, Void> {

  private static final Integer THUMBNAIL_SIZE = Integer.valueOf(System.getenv("THUMBNAIL_SIZE"));
  private static final String THUMBNAIL_PREFIX = "thumbnails/" + THUMBNAIL_SIZE + "x" + THUMBNAIL_SIZE + "-";

  @Override
  public Void handleRequest(S3Event s3Event, Context context) {
    String bucket = s3Event.getRecords().get(0).getS3().getBucket().getName();
    String key = s3Event.getRecords().get(0).getS3().getObject().getKey();
    System.out.println("Going to create a thumbnail for: " + bucket + "/" + key);

    AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    System.out.println("Connection to S3 established");

    try {
      File tempFile = File.createTempFile(key, ".tmp");
      s3Client.getObject(new GetObjectRequest(bucket, key), tempFile);
      System.out.println("Successfully read S3 object to local temp file");

      BufferedImage img = new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, BufferedImage.TYPE_INT_RGB);
      img.createGraphics().drawImage(ImageIO.read(tempFile).getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, null);
      File resizedTempFile = File.createTempFile(key, ".resized.tmp");
      ImageIO.write(img, "png", resizedTempFile);
      System.out.println("Successfully created resized image");

      String targetKey = THUMBNAIL_PREFIX + key.replace("uploads/", "");
      s3Client.putObject(bucket, targetKey, resizedTempFile);
      System.out.println("Successfully uploaded resized image with key " + targetKey);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
