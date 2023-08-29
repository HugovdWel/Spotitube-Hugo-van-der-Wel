package nl.han.danielvervloed.oose.spotitube.dao.exception;

public class QueryFailure extends RuntimeException {

    public QueryFailure(final String message){
        super(message);
    }
}
