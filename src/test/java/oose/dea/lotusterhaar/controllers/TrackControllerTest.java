package oose.dea.lotusterhaar.controllers;

import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.services.rest.TrackServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerTest {

    @Mock
    private TrackServiceImpl trackService;

    @InjectMocks
    private TrackController sut;

    @Test
    public void testStatusOKGetAllTracksFromPlaylist() throws TokenExpiredException {
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(trackService.getTracks(Mockito.anyInt(), Mockito.any())).thenReturn(tracksOverview);
        Response response = sut.getAllTracksFromPlaylist("1234-1234-1234", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedGetAllTracksFromPlaylist() throws TokenExpiredException {
        Mockito.when(trackService.getTracks(Mockito.anyInt(), Mockito.any())).thenThrow(new TokenExpiredException("Usertoken incorrect"));
        Response response = sut.getAllTracksFromPlaylist("1234-1234-1234", 1);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

}