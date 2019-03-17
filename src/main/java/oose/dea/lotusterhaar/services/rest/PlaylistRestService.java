package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.*;
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
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new AuthenticationException(tokenExpired);
        }
    }


    public Library deletePlaylistById(int id, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.deletePlaylistById(id);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    public Library addPlayList(String token, Playlist playlist) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.addPlaylist(token, playlist);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    public Library editNameOfPlaylist(int id, String token, String name) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.updateNameOfPlaylist(id, name);
            return playlistDAO.getAllPlaylists(token);

        } else {
            throw new AuthenticationException(tokenExpired);
        }
    }

    public Library deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return playlistDAO.getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    public TrackOverview addTrackToPlaylist(int id, String token, Track track) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.addTrackToPlaylist(id, track);
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
