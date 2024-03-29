/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package disks;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws URISyntaxException {
    //part 3
    Library library = new Library("Alexandria");
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

      library.readInput(sb.toString());
      Book b = library.searchBookByISBN("9780987654321");
      System.out.println(b.getTitle());

      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
