package HugoVanDerWel.Resources;

import HugoVanDerWel.DataTransferObjects.PlayListsDTO;
import HugoVanDerWel.Models.PlaylistModel;
import HugoVanDerWel.Models.TrackModel;
import HugoVanDerWel.Models.UserModel;
import HugoVanDerWel.persistence.UserPersistence;
import HugoVanDerWel.resources.PlaylistResource;
import HugoVanDerWel.services.AuthenticationService;
import HugoVanDerWel.services.Database;
import HugoVanDerWel.services.PlaylistService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PlatlistResourceTest {

    private PlaylistResource sut;
    private PlaylistService playlistService;

    private PlayListsDTO playListsDTO = new PlayListsDTO() {{
        PlaylistModel[] testPlaylistModel =
                new PlaylistModel[]{
                        new PlaylistModel() {{
                            id = 1;
                            name = "Gert";
                            owner = false;
                            tracks = new TrackModel[]{};
                        }},
                        new PlaylistModel() {{
                            id = 2;
                            name = "bas";
                            owner = true;
                            tracks = new TrackModel[]{};
                        }}
                };
        length = 2;
    }};
    private UserModel owner = new UserModel() {{
        token = "123123";
    }};

    @Before
    public void setup() {
        this.playlistService = Mockito.mock(PlaylistService.class);
        this.sut = new PlaylistResource(playlistService, new AuthenticationService(new UserPersistence(new Database())));
    }

    @Test
    public void GoodGetPlaylistsRequest() {
        //setup
        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenReturn(playListsDTO);

        //act
        PlayListsDTO response = (PlayListsDTO) sut.getPlaylists(owner.token).getEntity();

        //assert
        Assert.assertEquals(response, playListsDTO);
    }
}
