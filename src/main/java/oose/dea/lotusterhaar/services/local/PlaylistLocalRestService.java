package oose.dea.lotusterhaar.services.local;

import oose.dea.lotusterhaar.domain.*;

import java.util.ArrayList;

public class PlaylistLocalRestService {

    private ArrayList<Playlist> playlists = new ArrayList();
    private ArrayList<Track> tracklist1 = new ArrayList<>();
    private ArrayList<Track> tracklist2 = new ArrayList<>();
    private Library library;
    private Track track1, track2, track3, track4, track5;

    public PlaylistLocalRestService() {
        track1 = new SongTrack(1, "Song for Someone", "The Frames", 350, 0, false, "The cost");
        track2 = new VideoTrack(2, "The cost", "The Frames", 423, 37, true, "10-01-2005", "Title Song from the Album The Cost");
        track3 = new VideoTrack(3, "Falling Slowly", "The Frames", 436, 10, true, "10-01-2005", "Title Song from the Album The Cost");
        track4 = new SongTrack(4, "Love Story", "Taylor Swift", 380, 100, false, "Fearless");
        track5 = new SongTrack(5, "Girls Like You", "Maroon 5", 400, 0, true, "Red Pill Blues");
        tracklist1.add(track1);
        tracklist1.add(track2);
        tracklist1.add(track3);
        tracklist2.add(track4);
        tracklist2.add(track5);
        playlists.add(new Playlist(1, "Death metal", true, tracklist1));
        playlists.add(new Playlist(2, "Pop", true, tracklist2));
        library = new Library(playlists);
    }

    public TracksOverview getAllTracksFromPlaylist(int id, String token) throws Exception {
        for (Playlist playlist : library.getPlaylists()) {
            if (token.equals("1234-1234-1234-1234") && id == playlist.getId()) {
                return new TracksOverview(playlist.getTracks());
            }
        }
        throw new Exception("No tracks found!");
    }

    public Library getAllPlaylists() {
        return library;
    }
}
