package nl.han.danielvervloed.oose.spotitube.dto;

import java.util.List;

public class PlaylistsDTO {
    private List<PlaylistDTO> playlists;
    private int length;


    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
