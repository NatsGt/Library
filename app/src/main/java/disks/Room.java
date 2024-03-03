package disks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

  private String id;
  private Map<String, Row> rows;

  public Room(String id) {
    this.id = id;
    this.rows = new HashMap<>();
  }

  public void addRow(Row row) {
    rows.put(row.getId(), row);
  }

  public Row getRow(String id) {
    return rows.get(id);
  }

  public String getId() {
    return id;
  }

  public List<Book> getBooks() {
    List<Book> books = new ArrayList<>();
    for (Row row : rows.values()) {
      books.addAll(row.getBooks());
    }
    return books;
  }
}
