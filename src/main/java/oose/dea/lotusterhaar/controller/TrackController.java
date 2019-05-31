package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.model.TracksOverview;
import oose.dea.lotusterhaar.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import javax.ws.rs.QueryParam;

@RestController
public class TrackController {

    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping(path="/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    public TracksOverview getAllTracksFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") final int playlistId) {
        try {
            return trackService.getTracks(playlistId, token);
        } catch (TokenExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied", e);
        }
    }
}
