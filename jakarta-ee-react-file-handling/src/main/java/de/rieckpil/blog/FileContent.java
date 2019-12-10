package de.rieckpil.blog;

public class FileContent {

  private String fileName;
  private byte[] content;

  public FileContent() {
  }

  public FileContent(String fileName, byte[] content) {
    this.fileName = fileName;
    this.content = content;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
