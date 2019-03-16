package oose.dea.lotusterhaar.domain;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private ArrayList<Track> tracks;
    private int length;

    public Playlist() {
    }

    public Playlist(int id, String name, boolean owner, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
        this.length = getLength();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getDuration();
        }
        return totalLength;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
