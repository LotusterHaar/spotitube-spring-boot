package oose.dea.lotusterhaar;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.services.local.LoginLocalRestService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

public class LoginRestServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LoginLocalRestService sut; //system under test

    @Before
    public void SetUp() throws Exception {
        sut = new LoginLocalRestService();
    }

    @Test
    public void testSuccessfulLogin() throws LoginException {
        Account account = new Account("uwe", "uwepass");
        UserToken login = sut.login(account);
        assertEquals("Uwe van Heesch", login.getUser());
        assertEquals("1234-1234-1234", login.getToken());
    }

    @Test
    public void testFailedLogin() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Invalid login credentials");
        Account account = new Account("uwe", "uwepass");
    }

}