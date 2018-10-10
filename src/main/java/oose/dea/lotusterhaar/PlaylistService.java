package oose.dea.lotusterhaar;

public class PlaylistService {

    public TrackOverview getAllTracksFromPlaylist(int id, String token, Library library) throws Exception {
        for (Playlist playlist : library.getPlaylists()) {
            if (token.equals("1234-1234-1234-1234") && id == playlist.getId()) {
                return new TrackOverview(playlist.getTracks());
            }
        }
        throw new Exception("No tracks found!");
    }
}
