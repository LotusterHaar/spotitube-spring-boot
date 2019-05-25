package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.*;
import oose.dea.lotusterhaar.dao.PlaylistDAO;
import oose.dea.lotusterhaar.dao.TokenDAO;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;

@Named("playlistService")
public class PlaylistServiceImpl implements PlaylistService {
    private static final String tokenExpired = "Token has expired!";

    @Autowired
    private PlaylistDAO playlistDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public Library getAllPlaylists(String token) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        System.out.println(userToken);
        if (!tokenDAO.tokenExpired(userToken)) {
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview getAllTracksFromPlaylist(int id, String token) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library deletePlaylistById(int id, String token) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.deletePlaylistById(id);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public Library addPlaylist(String token, Playlist playlist) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.addPlaylist(token, playlist);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library editNameOfPlaylist(int id, String token, String name) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.updateNameOfPlaylist(id, name);
            return playlistDAO.getAllPlaylists(userToken);

        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return playlistDAO.getAllTracksFromPlaylist(playlistId);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview addTrackToPlaylist(int id, String token, Track track) throws TokenExpiredException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (!tokenDAO.tokenExpired(userToken)) {
            playlistDAO.addTrackToPlaylist(id, track);
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
