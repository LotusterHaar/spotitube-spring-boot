package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.*;
import oose.dea.lotusterhaar.dao.PlaylistDAO;
import oose.dea.lotusterhaar.dao.UserTokenRepository;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private static final String tokenExpired = "Token has expired!";

    private PlaylistDAO playlistDAO;

    private UserTokenRepository userTokenRepository;

    private UserTokenService userTokenService;

    public PlaylistServiceImpl(PlaylistDAO playlistDAO, UserTokenRepository userTokenRepository, UserTokenService userTokenService) {
        this.playlistDAO = playlistDAO;
        this.userTokenRepository = userTokenRepository;
        this.userTokenService = userTokenService;
    }


    @Override
    public Library getAllPlaylists(String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        System.out.println(userToken);
        if (!userTokenService.tokenExpired(userToken)) {
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview getAllTracksFromPlaylist(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library deletePlaylistById(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistDAO.deletePlaylistById(id);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public Library addPlaylist(String token, Playlist playlist) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistDAO.addPlaylist(token, playlist);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library editNameOfPlaylist(int id, String token, String name) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistDAO.updateNameOfPlaylist(id, name);
            return playlistDAO.getAllPlaylists(userToken);

        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return playlistDAO.getAllTracksFromPlaylist(playlistId);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview addTrackToPlaylist(int id, String token, Track track) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistDAO.addTrackToPlaylist(id, track);
            return playlistDAO.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
