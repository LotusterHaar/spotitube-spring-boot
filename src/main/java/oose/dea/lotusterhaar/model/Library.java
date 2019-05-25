package oose.dea.lotusterhaar.model;


import java.util.ArrayList;

public class Library {
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private int length;

    public Library() {
    }

    public Library(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private int getTotalLengthPlaylists() {
        int totalLength = 0;
        for (Playlist playlist : playlists) {
            totalLength += playlist.getLength();
        }
        return totalLength;
    }

}
