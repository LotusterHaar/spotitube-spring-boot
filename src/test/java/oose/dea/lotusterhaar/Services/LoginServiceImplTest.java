package oose.dea.lotusterhaar.Services;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.UserToken;
import oose.dea.lotusterhaar.persistence.AccountDAO;
import oose.dea.lotusterhaar.persistence.TokenDAO;
import oose.dea.lotusterhaar.services.rest.LoginServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

public class LoginServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private LoginServiceImpl sut;

    @Test
    public void testSuccessfulLogin() throws LoginException {
        Account account = new Account("lotus", "lotuspass");
        UserToken userToken = new UserToken("1234-1234-1234", "Lotus");
        Mockito.when(accountDAO.findAccountByUsername(Mockito.any())).thenReturn(account);
        Mockito.when(tokenDAO.createNewUserToken(Mockito.any())).thenReturn(userToken);
        sut.login(account);

        assertEquals("Lotus", userToken.getUser());
        assertEquals("1234-1234-1234", userToken.getToken());

    }

    @Test
    public void testFailedLogin() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Login failed");

        Account account = new Account("lotus", "lotuspass");
        Account account1 = new Account("lotus", "lotuswrongpass");
        Mockito.when(accountDAO.findAccountByUsername(Mockito.any())).thenReturn(account);
        sut.login(account1);
    }
}