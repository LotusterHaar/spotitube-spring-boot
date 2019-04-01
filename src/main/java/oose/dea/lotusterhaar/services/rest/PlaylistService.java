package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.Track;
import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;

public interface PlaylistService {
    Library getAllPlaylists(String token) throws TokenExpiredException;

    TracksOverview getAllTracksFromPlaylist(int id, String token) throws TokenExpiredException;

    Library deletePlaylistById(int id, String token) throws TokenExpiredException;

    Library addPlaylist(String token, Playlist playlist) throws TokenExpiredException;

    Library editNameOfPlaylist(int id, String token, String name) throws TokenExpiredException;

    TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws TokenExpiredException;

    TracksOverview addTrackToPlaylist(int id, String token, Track track) throws TokenExpiredException;
}
