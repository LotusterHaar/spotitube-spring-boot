package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.services.PlaylistRestService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/playlists")
public class PlaylistController {
    @Inject
    @Named("playlistRestService")
    private PlaylistRestService playlistService = new PlaylistRestService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Library getPlaylists() {
        Library library = playlistService.createLibrary();
        return library;
    }

    @Path("{id}/tracks")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("id") final int id, @QueryParam("token") String token) throws Exception {
        try {
            return Response.ok().entity(playlistService.getAllTracksFromPlaylist(id, token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

