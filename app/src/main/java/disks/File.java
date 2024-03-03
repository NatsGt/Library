package disks;

public class File {

  private String title;
  private String ISBN;

  public File(String title, String ISBN) {
    this.title = title;
    this.ISBN = ISBN;
  }

  public File() {
    this.title = "";
    this.ISBN = "";
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }
}
