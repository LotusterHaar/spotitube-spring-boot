package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.TrackOverview;
import oose.dea.lotusterhaar.persistence.PlaylistDAO;
import oose.dea.lotusterhaar.persistence.TokenDAO;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.AuthenticationException;

@Named("playlistRestService")
public class PlaylistRestService {
    private static final String tokenExpired = "Token has expired!";

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

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


    public Library deletePlaylistById(int id, String token) throws Exception {
        /*      boolean validToken = tokenDAO.tokenExpired(token);*/
        boolean validToken = true;
        if (validToken) {
            playlistDAO.deletePlaylistById(id);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    public Library addPlayList(String token, Playlist playlist) throws Exception {
        boolean validToken = true;
        if (validToken) {
            playlistDAO.addPlaylist(token, playlist);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    public Library editNameOfPlaylist(int id, String token, String name) throws Exception {
        if (token.equals("1234-1234-1234-1234")) {
            playlistDAO.updateNameOfPlaylist(id, name);
            return playlistDAO.getAllPlaylists(token);

        } else {
            throw new AuthenticationException("Usertoken is invalid");
        }
    }
}
