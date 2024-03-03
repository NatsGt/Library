package disks;

import java.util.ArrayList;
import java.util.List;

public class Bookshelf {

  private String id;
  private List<Book> books;

  public Bookshelf(String id) {
    this.id = id;
    this.books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public List<Book> getBooks() {
    return books;
  }

  public List<Book> searchBooks(String input) {
    BookManager searchEngine = new BookManager(this.books);
    return searchEngine.findBooks(input);
  }

  public Book searchBookByISBN(String isbn) {
    for (Book book : books) {
      if (book.getISBN().equals(isbn)) {
        return book;
      }
    }
    return null;
  }

  public String getId() {
    return id;
  }
}
