package oose.dea.lotusterhaar.services;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.TrackOverview;

import javax.inject.Named;

@Named("playlistRestService")
public class PlaylistRestService {

    public TrackOverview getAllTracksFromPlaylist(int id, String token, Library library) throws Exception {
        for (Playlist playlist : library.getPlaylists()) {
            if (token.equals("1234-1234-1234-1234") && id == playlist.getId()) {
                return new TrackOverview(playlist.getTracks());
            }
        }
        throw new Exception("No tracks found!");
    }
}
