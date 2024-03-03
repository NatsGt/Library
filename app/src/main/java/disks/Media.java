package disks;

public class Media extends File {

  private Track[] tracks;
  private String downloadLink;

  public Media(String title, String ISBN, Track[] tracks, String downloadLink) {
    super(title, ISBN);
    this.tracks = tracks;
    this.downloadLink = downloadLink;
  }

  public Media(String title, String ISBN, Track[] tracks) {
    super(title, ISBN);
    this.tracks = tracks;
  }
}
