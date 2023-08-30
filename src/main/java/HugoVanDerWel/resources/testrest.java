package HugoVanDerWel.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class testrest {


    public testrest() {
    }

    @GET
    public Response test(){
        return Response.ok().build();
    }
}
