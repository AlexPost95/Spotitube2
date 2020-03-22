package com.oose.dea;

import com.oose.dea.api.Spotitube;
import com.oose.dea.api.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.oose.dea.api.dto.TrackDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpotitubeTest {

    Spotitube spotitube;

    @BeforeEach
    public void setup(){
        spotitube = new Spotitube();
    }

    @Test
    public void helloTest(){
        String expected = "hello test";
        assertEquals(expected, spotitube.hello());
    }

    @Test
    public void getPlaylistByIdTest(){
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("First playlist");
        playlist.setTracks("");

        when(playlistDAO.getPlaylistById(1)).thenReturn(playlist);

        spotitube.setPlaylistDAO(playlistDAO);

        Response response = spotitube.getPlaylist(1);

        PlaylistDTO playlistDTO = (PlaylistDTO)response.getEntity();

        assertEquals("First playlist", playlistDTO.name);
        assertEquals(1, playlistDTO.id);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getTrackByIdTest(){
        ITrackDAO trackDAO = mock(ITrackDAO.class);

        Track track = new Track();
        track.setId(1);
        track.setTitle("Still Into You");
        track.setAlbum("Paramore");
        track.setPerformer("Paramore");
        track.setDuration(339);

        when(trackDAO.getTrackById(1)).thenReturn(track);

        spotitube.setTrackDAO(trackDAO);

        Response response = spotitube.getTrack(1);

        TrackDTO trackDTO = (TrackDTO)response.getEntity();

        assertEquals("Still Into You", trackDTO.title);
        assertEquals("Paramore", trackDTO.performer);
        assertEquals("Paramore", trackDTO.album);
        assertEquals(339, trackDTO.duration);
        assertEquals(1, trackDTO.id);
        assertEquals(200, response.getStatus());
    }

}
