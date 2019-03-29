package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.Track;
import oose.dea.lotusterhaar.services.rest.PlaylistService;

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
    @Named("playlistService")
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        try {
            return Response.ok().entity(playlistService.getAllPlaylists(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("{id}/tracks")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("id") final int id, @QueryParam("token") String token) {
        try {
            return Response.ok().entity(playlistService.getAllTracksFromPlaylist(id, token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
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
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        try {
            return Response.ok().entity(playlistService.addPlaylist(token, playlist)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
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
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{playlistId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") final int playlistId, @PathParam("trackId") final int trackId, @QueryParam("token") String token) {
        try {
            return Response.ok().entity(playlistService.deleteTrackFromPlaylist(playlistId, trackId, token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") final int id, @QueryParam("token") String token, Track track) {
        try {
            return Response.ok().entity(playlistService.addTrackToPlaylist(id, token, track)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}

