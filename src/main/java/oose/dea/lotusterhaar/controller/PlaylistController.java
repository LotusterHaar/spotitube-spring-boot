package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.model.Library;
import oose.dea.lotusterhaar.model.Playlist;
import oose.dea.lotusterhaar.model.Track;
import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.service.rest.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping(path = "/playlists", produces= MediaType.APPLICATION_JSON_VALUE)
    public Library getPlaylists(@RequestParam("token") String token) {
        try {
            return playlistService.getAllPlaylists(token);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }

    @GetMapping(path = "/playlists/{id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    public TracksOverview getAllTracksFromPlaylist(@PathVariable("id") final int id, @RequestParam("token") String token) {
        try {
            return playlistService.getAllTracksFromPlaylist(id, token);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }

    @DeleteMapping(path="/playlists/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Library deletePlaylistById(@PathVariable("id") final int id, @RequestParam("token") String token) {
        try {
            return playlistService.deletePlaylistById(id, token);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }

    @PostMapping(path="/playlists", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Library addPlaylist(@RequestParam("token") String token, Playlist playlist) {
        try {
            return playlistService.addPlaylist(token, playlist);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }

    @PutMapping(path="/playlists/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Library editNameOfPlaylist(@PathVariable("id") final int id, @RequestParam("token") String token, Playlist playlist) {
        try {
            return playlistService.editNameOfPlaylist(id, token, playlist.getName());
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }

    @DeleteMapping(path="playlists/{playlistId}/tracks/{trackId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TracksOverview deleteTrackFromPlaylist(@PathVariable("playlistId") final int playlistId, @PathVariable("trackId") final int trackId, @RequestParam("token") String token) {
        try {
            return playlistService.deleteTrackFromPlaylist(playlistId, trackId, token);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }


    @PostMapping(path="playlists/{id}/tracks", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TracksOverview addTrackToPlaylist(@PathVariable("id") final int id, @RequestParam("token") String token, Track track) {
        try {
            return playlistService.addTrackToPlaylist(id, token, track);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }


}

