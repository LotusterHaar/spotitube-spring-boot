package oose.dea.lotusterhaar.model;

import java.util.ArrayList;

public class TracksOverview {
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public TracksOverview() {
    }

    public TracksOverview(ArrayList<Track> tracks) {
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
