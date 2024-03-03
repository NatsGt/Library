package disks;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

public class LibraryTest {

  private String input;
  private Library library = new Library("Alexandria");

  //first add books from input.txt
  @Before
  public void setUp() throws URISyntaxException {
    URL resource = App.class.getResource("/library.txt");
    File file = Paths.get(resource.toURI()).toFile();
    try {
      Scanner scanner = new Scanner(file);
      StringBuffer sb = new StringBuffer();
      while (scanner.hasNextLine()) {
        String inpLine = scanner.nextLine().trim();
        if (inpLine.equals("")) {
          sb.append("-empty-" + "\n");
        } else {
          sb.append(inpLine + "\n");
        }
      }
      input = sb.toString();
      library.readInput(input);
      scanner.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testReadInput() {
    assertEquals(10, library.getNumberOfBooks());
  }

  @Test
  public void testCreateInventoryByRoom() {
    assertEquals(4, library.createInventoryByRoom("1").size());
  }

  @Test
  public void testCreateInventoryByRoom_empty() {
    assertEquals(0, library.createInventoryByRoom("5").size());
  }

  @Test
  public void testCreateInventoryByRow() {
    assertEquals(2, library.createInventoryByRow("1", "1").size());
  }

  @Test
  public void testCreateInventoryByRow_empty() {
    assertEquals(0, library.createInventoryByRow("1", "4").size());
  }

  @Test
  public void testCreateInventoryByBookshelf() {
    assertEquals(1, library.createInventoryByBookshelf("1", "1", "1").size());
  }

  @Test
  public void testCreateInventoryByBookshelf_2() {
    assertEquals(2, library.createInventoryByBookshelf("3", "3", "3").size());
  }

  @Test
  public void testCreateInventoryByBookshelf_empty() {
    assertEquals(0, library.createInventoryByBookshelf("1", "1", "3").size());
  }

  @Test
  public void testGetNumberOfBooks() {
    assertEquals(10, library.getNumberOfBooks());
  }

  @Test
  public void testSearchBookByISBN() {
    assertEquals(
      "Fairy Tales",
      library.searchBookByISBN("9780987654321").getTitle()
    );
  }
}
