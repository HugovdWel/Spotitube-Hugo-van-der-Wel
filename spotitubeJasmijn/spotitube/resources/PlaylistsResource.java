package nl.han.danielvervloed.oose.spotitube.resources;

import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.TrackDTO;
import nl.han.danielvervloed.oose.spotitube.service.LoginService;
import nl.han.danielvervloed.oose.spotitube.service.PlaylistsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.han.danielvervloed.oose.spotitube.service.TracksService;

@Path("/playlists")
public class PlaylistsResource {
    private LoginService loginService;
    private PlaylistsService playlistsService;
    private TracksService tracksService;

/////////These methods use the playlistsService/////////

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token){
        loginService.checkToken(token);

        return Response.ok(playlistsService.getAllPlaylists(loginService.getUserFromToken(token))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        loginService.checkToken(token);
        playlistsService.deletePlaylist(id);
        return Response.ok(playlistsService.getAllPlaylists(loginService.getUserFromToken(token))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO newPlaylist){
        loginService.checkToken(token);
        String owner = loginService.getUserFromToken(token);
        playlistsService.addPlaylist(newPlaylist, owner);
        return Response.ok(playlistsService.getAllPlaylists(loginService.getUserFromToken(token))).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO editedPlaylist){
        loginService.checkToken(token);
        playlistsService.editPlaylist(editedPlaylist.getName(), id);
        return Response.ok(playlistsService.getAllPlaylists(loginService.getUserFromToken(token))).build();
    }

/////////These methods use the tracksService/////////
    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksFromAPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId){
        loginService.checkToken(token);
        return Response.ok(tracksService.getAllTracksFromAPlaylist(playlistId)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId){
        loginService.checkToken(token);
        tracksService.deleteTrackFromPlaylist(playlistId, trackId);
        return Response.ok(tracksService.getAllTracksFromAPlaylist(playlistId)).build();
    }

    @POST
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO addedTrack){
        loginService.checkToken(token);
        tracksService.addTrackToPlaylist(addedTrack, playlistId);
        return Response.ok(tracksService.getAllTracksFromAPlaylist(playlistId)).build();
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistsDTO(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }

}
