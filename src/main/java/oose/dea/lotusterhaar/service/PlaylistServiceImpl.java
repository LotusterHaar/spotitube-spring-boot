package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.model.*;
import oose.dea.lotusterhaar.dao.PlaylistRepository;
import oose.dea.lotusterhaar.dao.UserTokenRepository;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private static final String tokenExpired = "Token has expired!";

    private PlaylistRepository playlistRepository;

    private UserTokenRepository userTokenRepository;

    private UserTokenService userTokenService;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserTokenRepository userTokenRepository, UserTokenService userTokenService) {
        this.playlistRepository = playlistRepository;
        this.userTokenRepository = userTokenRepository;
        this.userTokenService = userTokenService;
    }


    @Override
    public Library getAllPlaylists(String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        System.out.println(userToken);
        if (!userTokenService.tokenExpired(userToken)) {
            return playlistRepository.getAllPlaylists(userToken);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview getAllTracksFromPlaylist(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            return playlistRepository.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library deletePlaylistById(int id, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistRepository.deletePlaylistById(id);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public Library addPlaylist(String token, Playlist playlist) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistRepository.addPlaylist(token, playlist);
            return getAllPlaylists(token);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }


    @Override
    public Library editNameOfPlaylist(int id, String token, String name) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistRepository.updateNameOfPlaylist(id, name);
            return playlistRepository.getAllPlaylists(userToken);

        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistRepository.deleteTrackFromPlaylist(playlistId, trackId);
            return playlistRepository.getAllTracksFromPlaylist(playlistId);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }

    @Override
    public TracksOverview addTrackToPlaylist(int id, String token, Track track) throws TokenExpiredException {
        UserToken userToken = userTokenRepository.findByToken(token);
        if (!userTokenService.tokenExpired(userToken)) {
            playlistRepository.addTrackToPlaylist(id, track);
            return playlistRepository.getAllTracksFromPlaylist(id);
        } else {
            throw new TokenExpiredException(tokenExpired);
        }
    }
}
