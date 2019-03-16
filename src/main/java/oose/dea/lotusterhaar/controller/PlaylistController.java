package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.services.rest.PlaylistRestService;

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
    private PlaylistRestService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Library getPlaylists(@QueryParam("token") String token) {
        Library library = playlistService.getAllPlaylists(token);
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

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylistById(@PathParam("id") final int id, @QueryParam("token") String token) {
        try {
            return Response.ok().entity(playlistService.deletePlaylistById(id, token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayList(@QueryParam("token") String token, Playlist playlist) {
        try {
            return Response.ok().entity(playlistService.addPlayList(token, playlist)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editNameOfPlaylist(@PathParam("id") final int id, @QueryParam("token") String token, Playlist playlist) {
        try {
            return Response.ok().entity(playlistService.editNameOfPlaylist(id, token, playlist.getName())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}

