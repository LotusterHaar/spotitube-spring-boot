package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.TrackOverview;
import oose.dea.lotusterhaar.persistence.PlaylistDAO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.AuthenticationException;

@Named("playlistRestService")
public class PlaylistRestService {

    @Inject
    private PlaylistDAO playlistDAO;

    public Library getAllPlaylists(String token) {
        return playlistDAO.getAllPlaylists(token);
    }

    public TrackOverview getAllTracksFromPlaylist(int id, String token) throws Exception {
        if (token.equals("1234-1234-1234-1234")) {
            return playlistDAO.getAllTracksFromPlaylist(id);

        } else {
            throw new AuthenticationException("Usertoken is invalid");
        }
    }


}
