package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.model.Account;
import oose.dea.lotusterhaar.model.UserToken;
import oose.dea.lotusterhaar.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.LoginException;



@RestController
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(path = "/login", produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public UserToken login(Account user) {
        try {
            return loginService.login(user);
        } catch (LoginException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed", e);
        }
    }
}
