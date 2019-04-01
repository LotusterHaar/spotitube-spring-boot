package oose.dea.lotusterhaar.services;

import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.persistence.TokenDAO;
import oose.dea.lotusterhaar.persistence.TokenExpiredException;
import oose.dea.lotusterhaar.persistence.TrackDAO;
import oose.dea.lotusterhaar.services.rest.TrackServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrackServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private TrackServiceImpl sut;

    @Test
    public void testThatTrackOverviewIsReturnedIfUsertokenIsValidGetTracks() throws TokenExpiredException {
        UserToken userToken = new UserToken("1234-1234-1234", "lotus");
        TracksOverview tracksOverview = new TracksOverview();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(false);
        Mockito.when(trackDAO.getTracks(Mockito.anyInt())).thenReturn(tracksOverview);

        assertEquals(tracksOverview, sut.getTracks(1, "1234-1234-1234"));
    }


    @Test
    public void testThatExceptionIsReturnedIfUsertokenIsValidGetTracks() throws TokenExpiredException {
        thrown.expect(TokenExpiredException.class);
        thrown.expectMessage("Token has expired!");

        UserToken userToken = new UserToken("1234-1234-1234", "lotus");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.tokenExpired(Mockito.any(UserToken.class))).thenReturn(true);

        sut.getTracks(1, "1234-1234-1234");

    }
}