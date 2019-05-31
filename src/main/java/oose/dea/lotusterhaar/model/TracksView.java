package oose.dea.lotusterhaar.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TracksView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column
    private String performer;

    @Column
    private int duration;

    @Column
    private int playcount;

    @Column
    private boolean offlineAvailable;

    @Column
    private String album;

    @Column
    private LocalDateTime publicationDate;

    @Column
    private String description;

    public TracksView() {
        super();
    }

    public TracksView(int id, String title, String performer, int duration, int playcount, boolean offlineAvailable, String album, LocalDateTime publicationDate, String description) {
        super();
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
        this.album = album;
        this.publicationDate = publicationDate;
        this.description = description;
    }
}
