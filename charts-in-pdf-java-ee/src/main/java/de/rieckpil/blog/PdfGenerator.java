package de.rieckpil.blog;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.markers.SeriesMarkers;

@Stateless
public class PdfGenerator {

  public byte[] createPdf() throws IOException {
    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage(PDRectangle.A4);
      page.setRotation(90);

      float pageWidth = page.getMediaBox().getWidth();
      float pageHeight = page.getMediaBox().getHeight();

      PDPageContentStream contentStream = new PDPageContentStream(document, page);

      PDImageXObject chartImage = JPEGFactory.createFromImage(document,
        createChart((int) pageHeight, (int) pageWidth));

      contentStream.transform(new Matrix(0, 1, -1, 0, pageWidth, 0));
      contentStream.drawImage(chartImage, 0, 0);
      contentStream.close();

      document.addPage(page);

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      document.save(byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    }

  }

  private BufferedImage createChart(int width, int height) {
    XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(width).height(height)
      .theme(ChartTheme.Matlab).build();
    XYSeries series = chart.addSeries("Random", null, getRandomNumbers(200));
    series.setMarker(SeriesMarkers.NONE);
    return BitmapEncoder.getBufferedImage(chart);
  }

  private double[] getRandomNumbers(int numPoints) {
    double[] y = new double[numPoints];
    for (int i = 0; i < y.length; i++) {
      y[i] = ThreadLocalRandom.current().nextDouble(0, 1000);
    }
    return y;
  }

}
