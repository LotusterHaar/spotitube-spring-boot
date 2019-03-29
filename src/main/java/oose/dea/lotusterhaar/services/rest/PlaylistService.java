package oose.dea.lotusterhaar.services.rest;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.Track;
import oose.dea.lotusterhaar.domain.TracksOverview;

public interface PlaylistService {
    Library getAllPlaylists(String token) throws Exception;

    TracksOverview getAllTracksFromPlaylist(int id, String token) throws Exception;

    Library deletePlaylistById(int id, String token) throws Exception;

    Library addPlaylist(String token, Playlist playlist) throws Exception;

    Library editNameOfPlaylist(int id, String token, String name) throws Exception;

    TracksOverview deleteTrackFromPlaylist(int playlistId, int trackId, String token) throws Exception;

    TracksOverview addTrackToPlaylist(int id, String token, Track track) throws Exception;
}
