package disks;

import java.util.ArrayList;
import java.util.List;

public class Book extends File {

  private List<String> author;
  private int pages;
  private String publisher;
  private int published;

  public Book() {
    super();
    this.author = new ArrayList<>();
    this.pages = 0;
  }

  public void addAuthor(String author) {
    this.author.add(author);
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public void setPublished(int published) {
    this.published = published;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public void setTitle(String title) {
    super.setTitle(title);
  }

  public String getPublisher() {
    return publisher;
  }

  public int getPublished() {
    return published;
  }

  public List<String> getAuthor() {
    return author;
  }

  public String getISBN() {
    return super.getISBN();
  }

  public int getPages() {
    return pages;
  }
}
