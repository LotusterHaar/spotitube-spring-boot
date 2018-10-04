package oose.dea.lotusterhaar;

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
    private LoginService loginService;

    @InjectMocks
    private LoginController sut;

    @Test
    public void testStatusOKOnSuccesfullLogin() throws LoginException {
        UserToken userToken = new UserToken("", "");
        Mockito.when(loginService.login(Mockito.any())).thenReturn(userToken);
        Account account = new Account("", "");
        Response loginResponse = sut.getPassword(account);

        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
        assertEquals(userToken, loginResponse.getEntity());

    }
}