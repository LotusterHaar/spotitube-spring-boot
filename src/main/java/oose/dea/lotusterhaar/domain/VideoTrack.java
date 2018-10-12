package oose.dea.lotusterhaar.domain;

public class VideoTrack extends Track {
    private String publicationDate;
    private String description;

    public VideoTrack(int id, String title, String performer, int duration, int playcount, boolean offlineAvailable, String publicationDate, String description) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
