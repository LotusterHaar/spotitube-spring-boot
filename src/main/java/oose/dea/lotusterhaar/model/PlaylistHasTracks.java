package oose.dea.lotusterhaar.model;

import javax.persistence.*;

@Entity
public class PlaylistHasTracks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int playlistId;

    @Column(nullable = false)
    private int trackId;

    public PlaylistHasTracks(){}

    public PlaylistHasTracks(int playlistId, int trackId) {
        super();
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public int getTrackId() {
        return trackId;
    }
}
