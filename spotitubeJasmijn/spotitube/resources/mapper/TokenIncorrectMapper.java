package nl.han.danielvervloed.oose.spotitube.resources.mapper;

import nl.han.danielvervloed.oose.spotitube.service.exception.TokenIncorrect;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class TokenIncorrectMapper implements ExceptionMapper<TokenIncorrect> {

    @Override
    public Response toResponse(TokenIncorrect tokenIncorrect) {
        return Response.status(403).build();
    }
}
