package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.domain.*;
import oose.dea.lotusterhaar.services.PlaylistService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService = new PlaylistService();
    private List<Playlist> playlists = new ArrayList();
    private List<Track> tracklist1 = new ArrayList<>();
    private List<Track> tracklist2 = new ArrayList<>();
    private Library library;
    private Track track1, track2, track3, track4, track5;

    public PlaylistController() {
        track1 = new SongTrack(1, "Song for Someone", "The Frames", 350, 0, false, "The cost");
        track2 = new VideoTrack(2, "The cost", "The Frames", 423, 37, true, "10-01-2005", "Title Song from the Album The Cost");
        track3 = new VideoTrack(3, "Falling Slowly", "The Frames", 436, 10, true, "10-01-2005", "Title Song from the Album The Cost");
        track4 = new SongTrack(4, "Love Story", "Taylor Swift", 380, 100, false, "Fearless");
        track5 = new SongTrack(5, "Girls Like You", "Maroon 5", 400, 0, true, "Red Pill Blues");
        tracklist1.add(track1);
        tracklist1.add(track2);
        tracklist1.add(track3);
        tracklist2.add(track4);
        tracklist2.add(track5);
        playlists.add(new Playlist(1, "Death metal", true, tracklist1));
        playlists.add(new Playlist(2, "Pop", true, tracklist2));
        library = new Library(playlists);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Library getPlaylists() {
        library = new Library(playlists);
        return library;
    }

    @Path("{id}/tracks")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromPlaylist(@PathParam("id") final int id, @QueryParam("token") String token) throws Exception {
        try {
            return Response.ok().entity(playlistService.getAllTracksFromPlaylist(id, token, library)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

