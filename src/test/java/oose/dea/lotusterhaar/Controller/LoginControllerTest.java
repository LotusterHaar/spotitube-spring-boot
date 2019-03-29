package oose.dea.lotusterhaar.Controller;

import oose.dea.lotusterhaar.controllers.LoginController;
import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.services.rest.LoginServiceImpl;
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
public class LoginControllerTest {

    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private LoginController sut;

    @Test
    public void testStatusOKOnSuccessfulLogin() throws LoginException {
        UserToken userToken = new UserToken("1234-1234-1234", "testUser");
        Mockito.when(loginService.login(Mockito.any())).thenReturn(userToken);

        Account account = new Account("testUser", "secretpass");
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
        assertEquals(userToken, loginResponse.getEntity());

    }

    @Test
    public void testUnauthorizedOnFailedLogin() throws LoginException {
        Account account = new Account("newbee", "newpass");
        Mockito.when(loginService.login(Mockito.any())).thenThrow(new LoginException("Invalid login credentials"));
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }
}