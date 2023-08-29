package nl.han.danielvervloed.oose.spotitube.service;

import nl.han.danielvervloed.oose.spotitube.dao.PlaylistsDAO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistDTO;
import nl.han.danielvervloed.oose.spotitube.dto.PlaylistsDTO;

import javax.inject.Inject;

public class PlaylistsService {
    private PlaylistsDAO playlistsDAO;

    public PlaylistsDTO getAllPlaylists(String user){
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.setPlaylists(playlistsDAO.getAll(user));
        playlistsDTO.setLength(playlistsDAO.getTotalDuration());
        return playlistsDTO;
    }

    public void deletePlaylist(int id){
        playlistsDAO.deleteOne(id);
    }

    public void addPlaylist(PlaylistDTO newPlaylist, String username){
        playlistsDAO.addOne(newPlaylist.getName(), username);
    }

    public void editPlaylist(String newName, int id) {
        playlistsDAO.editOne(newName, id);
    }

    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

    public PlaylistsDAO getPlayListsDao(){
        return playlistsDAO;
    }


}
