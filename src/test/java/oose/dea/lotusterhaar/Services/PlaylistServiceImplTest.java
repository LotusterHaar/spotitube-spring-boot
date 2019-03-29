package oose.dea.lotusterhaar.Services;

import oose.dea.lotusterhaar.domain.*;
import oose.dea.lotusterhaar.persistence.PlaylistDAO;
import oose.dea.lotusterhaar.persistence.TokenDAO;
import oose.dea.lotusterhaar.services.rest.PlaylistServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTest {

    private static final String tokenIncorrect = "Usertoken incorrect!";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private PlaylistDAO playlistDAO;
    @Mock
    private TokenDAO tokenDAO;
    @InjectMocks
    private PlaylistServiceImpl sut;

    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidGetAllPlaylists() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.getAllPlaylists("1234-1234-1234"));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidGetAllPlaylists() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllPlaylists("1234-1234-1234");

    }


    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidGetAllTracksFromPlaylist() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(tracksOverview);

        assertEquals(tracksOverview, sut.getAllPlaylists("1234-1234-1234"));

    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidGetAllTracksFromPlaylist() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllTracksFromPlaylist(1, "1234-1234-1234");

    }

    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidDeletePlaylistById() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.deletePlaylistById(1, "1234-1234-1234"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidDeletePlaylistById() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deletePlaylistById(1, "1234-1234-1234");
    }

    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidAddPlaylist() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.addPlaylist("1234-1234-1234", new Playlist()));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidAddPlaylist() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addPlaylist("1234-1234-1234", new Playlist());
    }


    @Test
    public void testThatLibraryIsReturnedIfUserTokenIsValidEditNameOfPlaylist() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Library library = new Library();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(library);

        assertEquals(library, sut.editNameOfPlaylist(1, "1234-1234-1234", "New Playlist"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidDeleteTrackFromPlaylist() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deleteTrackFromPlaylist(1, 1, "1234-1234-1234");
    }

    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidEditNameOfPlaylist() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();
        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.any())).thenReturn(tracksOverview);

        assertEquals(tracksOverview, sut.editNameOfPlaylist(1, "1234-1234-1234", "New Playlist"));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidEditNameOfPlaylist() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.editNameOfPlaylist(1, "1234-1234-1234", "New Playlist");
    }


    @Test
    public void addTrackToPlaylist() throws Exception {

    }

    @Test
    public void testThatTracksOverviewIsReturnedIfUserTokenIsValidAddTrackToPlaylist() throws Exception {
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        TracksOverview tracksOverview = new TracksOverview();
        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllTracksFromPlaylist(Mockito.any())).thenReturn(tracksOverview);

        assertEquals(tracksOverview, sut.addTrackToPlaylist(1, "1234-1234-1234", new Track()));
    }

    @Test
    public void testThatExceptionIsReturnedIfUserTokenIsValidAddTrackToPlaylist() throws Exception {
        thrown.expect(LoginException.class);
        thrown.expectMessage(tokenIncorrect);

        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(!tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addTrackToPlaylist(1, "1234-1234-1234", new Track());
    }

}