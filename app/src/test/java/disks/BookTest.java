package disks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BookTest {

  @Test
  public void testAddAuthor() {
    Book book = new Book();
    book.addAuthor("Author1");
    book.addAuthor("Author2");
    assertEquals(2, book.getAuthor().size());
  }

  @Test
  public void testSetPublisher() {
    Book book = new Book();
    book.setPublisher("Publisher");
    assertEquals("Publisher", book.getPublisher());
  }

  @Test
  public void testSetPublished() {
    Book book = new Book();
    book.setPublished(2020);
    assertEquals(2020, book.getPublished());
  }

  @Test
  public void testSetPages() {
    Book book = new Book();
    book.setPages(100);
    assertEquals(100, book.getPages());
  }

  @Test
  public void testSetTitle() {
    Book book = new Book();
    book.setTitle("Title");
    assertEquals("Title", book.getTitle());
  }

  @Test
  public void testGetPublisher() {
    Book book = new Book();
    book.setPublisher("Publisher");
    assertEquals("Publisher", book.getPublisher());
  }

  @Test
  public void testGetPublished() {
    Book book = new Book();
    book.setPublished(2020);
    assertEquals(2020, book.getPublished());
  }

  @Test
  public void testGetAuthor() {
    Book book = new Book();
    book.addAuthor("Author1");
    book.addAuthor("Author2");
    assertEquals(2, book.getAuthor().size());
  }

  @Test
  public void testGetISBN() {
    Book book = new Book();
    book.setISBN("ISBN");
    assertEquals("ISBN", book.getISBN());
  }

  @Test
  public void testGetPages() {
    Book book = new Book();
    book.setPages(100);
    assertEquals(100, book.getPages());
  }
}
