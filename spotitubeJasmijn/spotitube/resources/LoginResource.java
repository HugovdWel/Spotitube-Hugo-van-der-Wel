package nl.han.danielvervloed.oose.spotitube.resources;

import nl.han.danielvervloed.oose.spotitube.dto.LoginRequestDTO;
import nl.han.danielvervloed.oose.spotitube.dto.LoginResponseDTO;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginResource {
    private LoginService loginService;
    final int RANDOM_MIN = 100000;
    final int RANDOM_MAX = 999999;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO){
        loginService.checkLogin(loginRequestDTO);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        String user = loginRequestDTO.getUser();
        String token = generateToken();
        loginResponseDTO.setUser(user);
        loginResponseDTO.setToken(token);
        loginService.setUsersToken(user, token);

        return Response.ok(loginResponseDTO).build();

    }

    public String generateToken() {
        int token = (int)(Math.random()*(RANDOM_MAX-RANDOM_MIN+1)+RANDOM_MIN);
        while (loginService.checkTokenNotUnique(Integer.toString(token))){
           token = (int)(Math.random()*(RANDOM_MAX-RANDOM_MIN+1)+RANDOM_MIN);
        }
        return Integer.toString(token);
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
