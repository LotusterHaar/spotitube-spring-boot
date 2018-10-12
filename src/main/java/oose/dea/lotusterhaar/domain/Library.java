package oose.dea.lotusterhaar.domain;

import java.util.List;

public class Library {
    private List<Playlist> playlists;
    private int length;


    public Library(List<Playlist> playlists) {
        this.playlists = playlists;
        this.length = getTotalLengthPlaylists();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
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
