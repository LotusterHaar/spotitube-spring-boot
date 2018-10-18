package oose.dea.lotusterhaar.controller;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.services.LoginRestService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/login")
public class LoginController extends HttpServlet {
    @Inject
    @Named("loginRestService")
    private LoginRestService loginRestService = new LoginRestService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassword(Account user) {
        try {
            return Response.ok().entity(loginRestService.login(user)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
