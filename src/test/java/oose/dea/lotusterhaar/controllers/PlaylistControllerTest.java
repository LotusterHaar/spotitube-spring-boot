package oose.dea.lotusterhaar.controllers;

import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.Track;
import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.services.rest.PlaylistServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {
    private static final String tokenIncorrect = "Usertoken incorrect!";
    @Mock
    private PlaylistServiceImpl playlistService;
    @InjectMocks
    private PlaylistController sut;

    @Test
    public void testStatusOKOnGetPlaylists() throws Exception {
        Library library = new Library();

        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenReturn(library);
        Response response = sut.getPlaylists("1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnGetPlaylists() throws Exception {
        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.getPlaylists("1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


    @Test
    public void testStatusOKOnGetAllTracksFromPlaylist() throws Exception {
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(playlistService.getAllTracksFromPlaylist(Mockito.anyInt(), Mockito.any())).thenReturn(tracksOverview);
        Response response = sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnGetAllTracksFromPlaylist() throws Exception {
        Mockito.when(playlistService.getAllTracksFromPlaylist(Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOKOnDeletePlaylistById() throws Exception {
        Library library = new Library();

        Mockito.when(playlistService.deletePlaylistById(Mockito.anyInt(), Mockito.any())).thenReturn(library);
        Response response = sut.deletePlaylistById(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnDeletePlaylistById() throws Exception {
        Mockito.when(playlistService.deletePlaylistById(Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.deletePlaylistById(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOKonAddPlaylist() throws Exception {
        Library library = new Library();

        Mockito.when(playlistService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenReturn(library);
        Response response = sut.addPlaylist("1234-1234-1234", new Playlist());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnCreatePlaylist() throws Exception {
        Mockito.when(playlistService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.addPlaylist("1234-1234-1234", new Playlist());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


    @Test
    public void testStatusOKOnEditNameOfPlaylist() throws Exception {
        Library library = new Library();

        Mockito.when(playlistService.editNameOfPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(library);
        Response response = sut.editNameOfPlaylist(1, "1234-1234-1234", new Playlist());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnEditNameOfPlaylist() throws Exception {
        Mockito.when(playlistService.editNameOfPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.editNameOfPlaylist(1, "1234-1234-1234", new Playlist());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOKOnDeleteTrackFromPlaylist() throws Exception {
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(playlistService.deleteTrackFromPlaylist(Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenReturn(tracksOverview);
        Response response = sut.deleteTrackFromPlaylist(1, 1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnDeleteTrackFromPlaylist() throws Exception {
        Mockito.when(playlistService.deleteTrackFromPlaylist(Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.deleteTrackFromPlaylist(1, 1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOKOnAddTrackToPlaylist() throws Exception {
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(playlistService.addTrackToPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any(Track.class))).thenReturn(tracksOverview);
        Response response = sut.addTrackToPlaylist(1, "1234-1234-1234", new Track());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());

    }

    @Test
    public void testStatusUnauthorizedOnAddTrackToPlaylist() throws Exception {
        Mockito.when(playlistService.addTrackToPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any(Track.class))).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.addTrackToPlaylist(1, "1234-1234-1234", new Track());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}
