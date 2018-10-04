package oose.dea.lotusterhaar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassword(Account user) {
        if (user.getUser().equals("meron") && user.getPassword().equals("meronpass")) {

            return Response.ok(new UserToken("1234-1234-1234-1234", "Meron Brouwer")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
