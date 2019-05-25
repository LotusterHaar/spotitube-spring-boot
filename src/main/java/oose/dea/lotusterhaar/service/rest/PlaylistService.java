package oose.dea.lotusterhaar.service.rest;

import oose.dea.lotusterhaar.model.Library;
import oose.dea.lotusterhaar.model.Playlist;
import oose.dea.lotusterhaar.model.Track;
import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.dao.TokenExpiredException;

public interface PlaylistService {
    Library getAllPlaylists(String token) throws TokenExpiredException;

    TracksOverview getAllTracksFromPlaylist(int id, String token) throws TokenExpiredException;

    Library deletePlaylistById(int id, String token) throws TokenExpiredException;

    Library addPlaylist(String token, Playlist playlist) throws TokenExpiredException;

    Library editNameOfPlaylist(int id, String token, String name) throws TokenExpiredException;

    TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException;

    TracksOverview addTrackToPlaylist(int id, String token, Track track) throws TokenExpiredException;
}
