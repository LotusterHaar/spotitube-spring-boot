package oose.dea.lotusterhaar.Controller;

import oose.dea.lotusterhaar.controllers.TrackController;
import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.services.rest.TrackServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerTest {

    @Mock
    private TrackServiceImpl trackService;

    @InjectMocks
    private TrackController sut;

    @Test
    public void testStatusOKGetAllTracksFromPlaylist() throws Exception {
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(trackService.getTracks(Mockito.anyInt(), Mockito.any())).thenReturn(tracksOverview);
        Response response = sut.getAllTracksFromPlaylist("1234-1234-1234", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedGetAllTracksFromPlaylist() throws Exception {
        Mockito.when(trackService.getTracks(Mockito.anyInt(), Mockito.any())).thenThrow(new LoginException("Usertoken incorrect"));
        Response response = sut.getAllTracksFromPlaylist("1234-1234-1234", 1);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

}