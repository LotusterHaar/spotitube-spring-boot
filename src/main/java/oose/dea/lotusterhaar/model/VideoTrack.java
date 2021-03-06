package oose.dea.lotusterhaar.model;

import javax.persistence.Column;

public class VideoTrack extends Track {
    @Column
    private String publicationDate;

    @Column
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
