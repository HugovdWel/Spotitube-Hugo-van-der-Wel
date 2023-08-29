package nl.han.danielvervloed.oose.spotitube.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import nl.han.danielvervloed.oose.spotitube.service.TracksService;

@Path("/tracks")
public class TracksResource {
	private LoginService loginService;
	private TracksService tracksService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAvailableTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId){
		loginService.checkToken(token);

		return Response.ok(tracksService.getAllAvailableTracks(playlistId)).build();
	}

	@Inject
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Inject
	public void setTracksService(TracksService tracksService) {
		this.tracksService = tracksService;
	}

}
