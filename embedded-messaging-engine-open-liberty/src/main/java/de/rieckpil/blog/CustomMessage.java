package de.rieckpil.blog;

public class CustomMessage {

    private String content;
    private String author;
    private long createdAt;

    public CustomMessage() {

    }

    public CustomMessage(String content, String author, long createdAt) {
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
