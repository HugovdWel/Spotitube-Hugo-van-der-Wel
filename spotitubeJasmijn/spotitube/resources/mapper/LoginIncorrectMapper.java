package nl.han.danielvervloed.oose.spotitube.resources.mapper;

import nl.han.danielvervloed.oose.spotitube.service.exception.LoginIncorrect;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class LoginIncorrectMapper implements ExceptionMapper<LoginIncorrect> {

    @Override
    public Response toResponse(LoginIncorrect loginIncorrect) {
        return Response.status(401).build();
    }
}
