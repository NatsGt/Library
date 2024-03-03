package disks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {

  private String id;
  private Map<String, Bookshelf> bookshelves;

  public Row(String id) {
    this.id = id;
    this.bookshelves = new HashMap<>();
  }

  public void addBookshelf(Bookshelf bookshelf) {
    bookshelves.put(bookshelf.getId(), bookshelf);
  }

  public Bookshelf getBookshelf(String id) {
    return bookshelves.get(id);
  }

  public String getId() {
    return id;
  }

  public List<Book> getBooks() {
    List<Book> books = new ArrayList<>();
    for (Bookshelf bookshelf : bookshelves.values()) {
      books.addAll(bookshelf.getBooks());
    }
    return books;
  }
}
