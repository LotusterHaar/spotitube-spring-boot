package oose.dea.lotusterhaar.Controller;

import oose.dea.lotusterhaar.controller.PlaylistController;
import oose.dea.lotusterhaar.domain.Library;
import oose.dea.lotusterhaar.domain.Playlist;
import oose.dea.lotusterhaar.domain.TrackOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.services.rest.PlaylistRestService;
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
    private PlaylistRestService playlistRestService;
    @InjectMocks
    private PlaylistController sut;

    @Test
    public void testStatusOKOnGetPlaylists() throws Exception {
        Library library = new Library();
        Mockito.when(playlistRestService.getAllPlaylists(Mockito.any())).thenReturn(library);
        Response response = sut.getPlaylists("1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnGetPlaylists() throws Exception {
        Mockito.when(playlistRestService.getAllPlaylists(Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.getPlaylists("1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


    @Test
    public void testStatusOKOnGetAllTracksFromPlaylist() throws Exception {
        TrackOverview trackOverview = new TrackOverview();

        Mockito.when(playlistRestService.getAllTracksFromPlaylist(Mockito.anyInt(), Mockito.any())).thenReturn(trackOverview);
        Response response = sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(trackOverview, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnGetAllTracksFromPlaylist() throws Exception {
        Mockito.when(playlistRestService.getAllTracksFromPlaylist(Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePlaylistById() {
    }

    //Tests for deletePlaylist
    @Test
    public void testStatusOKOnDeletePlaylistById() throws Exception {
        Library library = new Library();

        Mockito.when(playlistRestService.deletePlaylistById(Mockito.anyInt(), Mockito.any())).thenReturn(library);
        Response response = sut.deletePlaylistById(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnDeletePlaylistById() throws Exception {
        Mockito.when(playlistRestService.deletePlaylistById(Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.deletePlaylistById(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void addPlayList() {
    }

    @Test
    public void testStatusOKonAddPlaylist() throws Exception {
        Library library = new Library();

        Mockito.when(playlistRestService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenReturn(library);
        Response response = sut.addPlaylist("1234-1234-1234", new Playlist());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusCreatePlaylistUnauthorizedOnIncorrectToken() throws Exception {
        Mockito.when(playlistRestService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenThrow(new TokenExpiredException(tokenIncorrect);
        Response response = sut.addPlaylist("1234-1234-1234", new Playlist());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


    @Test
    public void editNameOfPlaylist() {
    }

    @Test
    public void testStatusOKOnEditNameOfPlaylist() throws Exception {
        Library library = new Library();

        Mockito.when(playlistRestService.editNameOfPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(library);
        Response response = sut.editNameOfPlaylist(1, "1234-1234-1234", new Playlist());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(library, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnEditNameOfPlaylist() throws Exception {
        Mockito.when(playlistRestService.editNameOfPlaylist(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenThrow(new TokenExpiredException(tokenIncorrect));
        Response response = sut.editNameOfPlaylist(1, "1234-1234-1234", new Playlist());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteTrackFromPlaylist() {
    }

    @Test
    public void addTrackToPlaylist() {
    }
}
