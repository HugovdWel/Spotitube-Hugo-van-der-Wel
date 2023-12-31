package HugoVanDerWel.resources;

import HugoVanDerWel.DataTransferObjects.LoginRequestDTO;
import HugoVanDerWel.Models.UserModel;
import HugoVanDerWel.services.AuthenticationService;
import jakarta.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/login")
public class LoginResource {

    private AuthenticationService authenticationService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO) {
        UserModel userModel = new UserModel(){{
            username = loginRequestDTO.username;
            password = loginRequestDTO.password;
        }};
        if (!authenticationService.verifyPassword(userModel)) {
            return Response.status(304).build();
        }
        userModel = authenticationService.generateNewTokenForUser(userModel);
        userModel.password = null;
        return Response.status(200).entity(userModel).build();
    }
}
