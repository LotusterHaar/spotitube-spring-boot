package oose.dea.lotusterhaar.domain;

import java.util.ArrayList;

public class TrackOverview {
    ArrayList<Track> tracks = new ArrayList<Track>();

    public TrackOverview() {
    }

    public TrackOverview(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public void addTracks(ArrayList<Track> tracks) {
        this.tracks.addAll(tracks);
    }
}
