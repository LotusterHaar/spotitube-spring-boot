package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.model.*;
import oose.dea.lotusterhaar.dao.PlaylistDAO;
import oose.dea.lotusterhaar.dao.TokenDAO;
import oose.dea.lotusterhaar.dao.TokenExpiredException;
import oose.dea.lotusterhaar.service.rest.PlaylistServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTest {

    private static final String tokenIncorrect = "Token has expired!";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    //getAllPlaylists()
    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidGetAllPlaylists() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.getAllPlaylists("1234-1234-1234"));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidGetAllPlaylists() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.getAllPlaylists("1234-1234-1234");

    }

    //getAllTracksFromPlaylist()
    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidGetAllTracksFromPlaylist() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(tracksOverview);

        assertEquals(tracksOverview, sut.getAllTracksFromPlaylist(1, "1234-1234-1234"));

    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidGetAllTracksFromPlaylist() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

    }

    //deletePlaylistById()
    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidDeletePlaylistById() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.deletePlaylistById(1, "1234-1234-1234"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidDeletePlaylistById() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.deletePlaylistById(1, "1234-1234-1234");
    }

    //addPlaylist
    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidAddPlaylist() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.addPlaylist("1234-1234-1234", new Playlist()));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidAddPlaylist() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.addPlaylist("1234-1234-1234", new Playlist());
    }

    //editNameOfPlaylist()
    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidEditNameOfPlaylist() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.editNameOfPlaylist(1, "1234-1234-1234", "New Playlist"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidEditNameOfPlaylist() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.editNameOfPlaylist(1, "1234-1234-1234", "New Playlist");
    }

    //deleteTrackFromPlaylist()
    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidDeleteTrackFromPlaylist() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();
        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(tracksOverview);


        assertEquals(tracksOverview, sut.deleteTrackFromPlaylist(1, 1, "1234-1234-1234"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidDeleteTrackFromPlaylist() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.deleteTrackFromPlaylist(1, 1, "1234-1234-1234");
    }

    // addTrackToPlaylist()
    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidAddTrackToPlaylist() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();
        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(tracksOverview);


        assertEquals(tracksOverview, sut.addTrackToPlaylist(1, "1234-1234-1234", new Track()));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidAddTrackToPlaylist() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.addTrackToPlaylist(1, "1234-1234-1234", new Track());
    }

}