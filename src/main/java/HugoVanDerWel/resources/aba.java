package HugoVanDerWel.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class aba {

    @GET
    public Response test(){
        return Response.ok().build();
    }
}
