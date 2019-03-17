package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.services.rest.TrackRestService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/tracks")
public class TrackController {

    @Inject
    @Named("trackRestService")
    private TrackRestService trackRestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") final int playlistId) {
        try {
            return Response.ok().entity(trackRestService.getTracks(playlistId, token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
