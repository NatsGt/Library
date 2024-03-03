package disks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Library {

  private String name;
  private List<Room> rooms;
  private Map<String, String[]> inventory;

  public Library(String name) {
    this.name = name;
    rooms = new ArrayList<>();
    inventory = new java.util.HashMap<>();
    // Asume library has 4 rooms, each room has 3 rows, each row has 3 bookshelves
    for (int i = 0; i < 4; i++) {
      rooms.add(new Room(Integer.toString(i + 1)));
    }
    for (Room room : rooms) {
      for (int i = 0; i < 3; i++) {
        room.addRow(new Row(Integer.toString(i + 1)));
        Row row = room.getRow(Integer.toString(i + 1));
        for (int j = 0; j < 3; j++) {
          row.addBookshelf(new Bookshelf(Integer.toString(j + 1)));
        }
      }
    }
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public Room getRoom(String id) {
    for (Room room : rooms) {
      if (room.getId().equals(id)) {
        return room;
      }
    }
    return null;
  }

  private void addBook(
    Book book,
    String roomId,
    String rowId,
    String bookshelfId
  ) {
    Room room = getRoom(roomId);
    if (room == null) {
      System.out.println("Room not found");
    }
    Row row = room.getRow(rowId);
    if (row == null) {
      System.out.println("Row not found");
      return;
    }
    Bookshelf bookshelf = row.getBookshelf(bookshelfId);
    if (bookshelf == null) {
      System.out.println("Bookshelf not found");
      return;
    }
    bookshelf.addBook(book);
    inventory.put(book.getISBN(), new String[] { roomId, rowId, bookshelfId });
  }

  private void parseData(String[] data) {
    Book book = new Book();
    String roomId = "";
    String rowId = "";
    String bookshelfId = "";
    for (String string : data) {
      if (string.contains("Author")) {
        String[] author = string.split(":");
        book.addAuthor(author[1].trim());
      } else if (string.contains("Title")) {
        String[] title = string.split(":");
        book.setTitle(title[1].trim());
      } else if (string.contains("Publisher")) {
        String[] publisher = string.split(":");
        book.setPublisher(publisher[1].trim());
      } else if (string.contains("Published")) {
        String[] published = string.split(":");
        String year = published[1].trim();
        book.setPublished(Integer.parseInt(year));
      } else if (string.contains("NumberOfPages")) {
        String[] pages = string.split(":");
        String page = pages[1].trim();
        book.setPages(Integer.parseInt(page));
      } else if (string.contains("ISBN")) {
        String[] isbn = string.split(":");
        book.setISBN(isbn[1].trim());
      } else if (string.contains("Room")) {
        String[] room = string.split(":");
        roomId = room[1].trim();
      } else if (string.contains("Row")) {
        String[] row = string.split(":");
        rowId = row[1].trim();
      } else if (string.contains("Bookshelf")) {
        String[] bookshelf = string.split(":");
        bookshelfId = bookshelf[1].trim();
      }
    }
    addBook(book, roomId, rowId, bookshelfId);
  }

  public void readInput(String input) {
    String[] lines = input.split("-empty-");
    for (String bookData : lines) {
      String[] data = bookData.split("\n");
      parseData(data);
    }
  }

  public Book searchBookByISBN(String isbn) {
    String[] ids = inventory.get(isbn);
    if (ids == null) return null;
    Room room = getRoom(ids[0]);
    Row row = room.getRow(ids[1]);
    Bookshelf bookshelf = row.getBookshelf(ids[2]);
    return bookshelf.searchBookByISBN(isbn);
  }

  public List<Book> createInventoryByRoom(String id) {
    List<Book> books = new ArrayList<>();
    Room room = getRoom(id);
    if (room == null) return books;
    books.addAll(room.getBooks());
    return books;
  }

  public List<Book> createInventoryByRow(String roomId, String id) {
    List<Book> books = new ArrayList<>();
    Room room = getRoom(roomId);
    if (room == null) return books;
    Row row = room.getRow(id);
    if (row == null) return books;
    books.addAll(row.getBooks());
    return books;
  }

  public List<Book> createInventoryByBookshelf(
    String roomId,
    String rowId,
    String id
  ) {
    List<Book> books = new ArrayList<>();
    Room room = getRoom(roomId);
    if (room == null) return books;
    Row row = room.getRow(rowId);
    if (row == null) return books;
    Bookshelf bookshelf = row.getBookshelf(id);
    if (bookshelf == null) return books;
    books.addAll(bookshelf.getBooks());
    return books;
  }

  public int getNumberOfBooks() {
    return inventory.size();
  }
}
