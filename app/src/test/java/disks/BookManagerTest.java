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

public class BookManagerTest {

  private String input;
  private BookManager library = new BookManager();

  //first add books from input.txt
  @Before
  public void setUp() throws URISyntaxException {
    URL resource = App.class.getResource("/input.txt");
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
      library.readBooks(input);
      scanner.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testReadBooks() {
    BookManager library2 = new BookManager();
    assertEquals(2, library2.readBooks(input).size());
  }

  @Test
  public void testFindBooks() {
    assertEquals(1, library.findBooks("jensen & *01* & *ande*").size());
  }

  @Test
  public void testFindBooks_empty() {
    assertEquals(0, library.findBooks("").size());
  }

  @Test
  public void testFindBooks_noMatch() {
    assertEquals(0, library.findBooks("jensen & *01* & *andee*").size());
  }

  @Test
  public void testFindBooks_twoQueries_match() {
    assertEquals(2, library.findBooks("jensen & *01*").size());
  }

  @Test
  public void testFindBooks_twoQueries_noMatch() {
    assertEquals(0, library.findBooks("brian & borgen").size());
  }

  @Test
  public void testFindBooks_oneQueryStrict() {
    assertEquals(1, library.findBooks("stories").size());
  }

  @Test
  public void testFindBooks_oneQueryStart() {
    assertEquals(1, library.findBooks("*ries").size());
  }

  @Test
  public void testFindBooks_oneQueryEnd() {
    assertEquals(1, library.findBooks("stor*").size());
  }

  @Test
  public void testFindBooks_oneQueryStartEnd() {
    assertEquals(1, library.findBooks("*tori*").size());
  }

  @Test
  public void testFindBooks_numberStrict() {
    assertEquals(0, library.findBooks("01").size());
  }

  @Test
  public void testFindBooks_numberStart() {
    assertEquals(1, library.findBooks("*01").size());
  }

  @Test
  public void testFindBooks_numberEnd() {
    assertEquals(0, library.findBooks("01*").size());
  }

  @Test
  public void testFindBooks_numberStartEnd() {
    assertEquals(2, library.findBooks("*01*").size());
  }

  @Test
  public void searchByTitle_one() {
    assertEquals(1, library.findBooks("stories").size());
  }

  @Test
  public void searchByTitle_many() {
    assertEquals(2, library.findBooks("from").size());
  }

  @Test
  public void searchByTitle_empty() {
    assertEquals(0, library.findBooks("empty").size());
  }

  @Test
  public void searchByAuthor() {
    assertEquals(2, library.findBooks("Jensen").size());
  }

  @Test
  public void searchByPublisher() {
    assertEquals(1, library.findBooks("Borgen").size());
  }

  @Test
  public void searchByPublished() {
    assertEquals(1, library.findBooks("2012").size());
  }
}
