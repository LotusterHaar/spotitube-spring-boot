package oose.dea.lotusterhaar.domain;

public class SongTrack extends Track {
    private String album;

    public SongTrack(int id, String title, String performer, int duration, int playcount, boolean offlineAvailable, String album) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
