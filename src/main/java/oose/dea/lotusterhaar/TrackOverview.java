package oose.dea.lotusterhaar;

import java.util.List;

public class TrackOverview {
    List<Track> tracks;

    public TrackOverview() {
    }

    public TrackOverview(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
