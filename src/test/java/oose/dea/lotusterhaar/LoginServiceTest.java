package oose.dea.lotusterhaar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

public class LoginServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LoginService sut; //system under test

    @Before
    public void SetUp() throws Exception {
        sut = new LoginService();
    }

    @Test
    public void testSuccesfulLogin() throws LoginException {
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