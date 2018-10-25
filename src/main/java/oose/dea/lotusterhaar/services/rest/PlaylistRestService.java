package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.TrackOverview;
import oose.dea.lotusterhaar.persistence.PlaylistDAO;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class PlaylistRestService {

    @Inject
    private PlaylistDAO playlistDAO;

    public Library getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public TrackOverview getAllTracksFromPlaylist(int id, String token) throws Exception {
        if (token.equals("1234-1234-1234-1234")) {
            return playlistDAO.getAllTracksFromPlaylist(id);

        } else {
            throw new AuthenticationException("Usertoken is invalid");
        }
    }


}
