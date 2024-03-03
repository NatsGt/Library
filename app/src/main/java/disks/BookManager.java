package disks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookManager {

  private List<Book> files;

  public BookManager() {
    files = new ArrayList<>();
  }

  public BookManager(List<Book> files) {
    this.files = files;
  }

  private Book createBook(String[] data) {
    Book book = new Book();
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
      }
    }
    return book;
  }

  public List<Book> readBooks(String input) {
    if (input.equals("")) return new ArrayList<>();
    String[] lines = input.split("-empty-");
    for (String book : lines) {
      String[] data = book.split("\n");
      files.add(createBook(data));
    }
    return files;
  }

  private boolean findBook(Pattern pattern, String input, Book book) {
    //title
    Matcher matcher = pattern.matcher(book.getTitle());
    if (matcher.find()) {
      return true;
    }
    //publisher
    matcher = pattern.matcher(book.getPublisher());
    if (matcher.find()) {
      return true;
    }
    //published
    matcher = pattern.matcher(Integer.toString(book.getPublished()));
    if (matcher.find()) {
      return true;
    }
    //authors
    for (String author : book.getAuthor()) {
      matcher = pattern.matcher(author);
      if (matcher.find()) {
        return true;
      }
    }
    return false;
  }

  private List<Book> searchAnyStartAnyEnd(String input) {
    String search = input.substring(1, input.length() - 1);
    String regex = ".*" + search + ".*";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : files) {
      if (findBook(pattern, input, book)) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  private List<Book> searchAnyStart(String input) {
    String search = input.substring(1);
    String regex = ".*" + search + "\\b";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : files) {
      if (findBook(pattern, input, book)) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  private List<Book> searchAnyEnd(String input) {
    String search = input.substring(0, input.length() - 1);
    String regex = "\\b" + search + ".*";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : files) {
      if (findBook(pattern, input, book)) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  private List<Book> searchStringStrict(String input) {
    String regex = "\\b" + input + "\\b";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    List<Book> foundBooks = new ArrayList<>();
    for (Book book : files) {
      if (findBook(pattern, input, book)) {
        foundBooks.add(book);
      }
    }
    return foundBooks;
  }

  private List<Book> searchQuery(String input) {
    String startAndEnd = "\\*.*\\*";
    Pattern pattern = Pattern.compile(startAndEnd);
    Matcher matcher = pattern.matcher(input);
    if (matcher.find()) {
      return searchAnyStartAnyEnd(input);
    }
    String onlyEnd = "^[^*].*\\*";
    Pattern pattern2 = Pattern.compile(onlyEnd);
    Matcher matcher2 = pattern2.matcher(input);
    if (matcher2.find()) {
      return searchAnyEnd(input);
    }
    String onlyStart = "\\*.*[^*]$";
    Pattern pattern3 = Pattern.compile(onlyStart);
    Matcher matcher3 = pattern3.matcher(input);
    if (matcher3.find()) {
      return searchAnyStart(input);
    }
    return searchStringStrict(input);
  }

  public List<Book> findBooks(String input) {
    if (input.equals("")) return new ArrayList<>();
    String regex = ".+\\&.+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if (matcher.find()) {
      String[] queries = input.split("&");
      List<Book> foundBooks = searchQuery(queries[0].trim());
      for (int i = 1; i < queries.length; i++) {
        List<Book> temp = searchQuery(queries[i].trim());
        foundBooks.retainAll(temp);
      }
      return foundBooks;
    } else {
      return searchQuery(input.trim());
    }
  }
}
