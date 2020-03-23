package com.oose.dea;

import com.oose.dea.api.Spotitube;
import com.oose.dea.api.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.api.oose.dea.api.dto.TrackDTO;
import com.oose.dea.api.oose.dea.api.dto.TracksDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

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
    public void loginTest(){
       //TODO
    }

    @Test
    public void getAllPlaylistsTest(){

        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        Playlist playlist1 = new Playlist();
        playlist1.setId(1);
        playlist1.setName("First playlist");

        Playlist playlist2 = new Playlist();
        playlist2.setId(2);
        playlist2.setName("Second playlist");

        playlists.add(playlist1);
        playlists.add(playlist2);

        when(playlistDAO.getPlaylists()).thenReturn(playlists);

        spotitube.setPlaylistDAO(playlistDAO);

        Response response = spotitube.getPlaylists();

        PlaylistsDTO playlistsDTO = (PlaylistsDTO)response.getEntity();
        assertEquals("First playlist", playlistsDTO.playlists.get(0).name);
        assertEquals("Second playlist", playlistsDTO.playlists.get(1).name);
        assertEquals(1, playlistsDTO.playlists.get(0).id);
        assertEquals(2, playlistsDTO.playlists.get(1).id);
        assertEquals(2, playlistsDTO.playlists.size());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getPlaylistTest(){
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
    public void deletePlaylistTest(){
    //TODO
    }

    @Test
    public void addPlaylistTest(){
    //TODO
    }

    @Test
    public void updatePlaylistTest(){
    //TODO
    }

    @Test
    public void getTracksTest(){

        ITrackDAO trackDAO = mock(ITrackDAO.class);

        ArrayList<Track> tracks = new ArrayList<Track>();

        Track track1 = new Track();
        track1.setId(1);
        track1.setTitle("track1title");
        track1.setPerformer("track1performer");
        track1.setAlbum("track1album");

        Track track2 = new Track();
        track2.setId(2);
        track2.setTitle("track2title");
        track2.setPerformer("track2performer");
        track2.setAlbum("track2album");

        tracks.add(track1);
        tracks.add(track2);

        when(trackDAO.getTracks(0)).thenReturn(tracks);

        spotitube.setTrackDAO(trackDAO);

        Response response = spotitube.getTracks(0);

        TracksDTO tracksDTO = (TracksDTO)response.getEntity();

        assertEquals("track1title", tracksDTO.tracks.get(0).title);
        assertEquals("track2title", tracksDTO.tracks.get(1).title);
        assertEquals("track1performer", tracksDTO.tracks.get(0).performer);
        assertEquals("track2performer", tracksDTO.tracks.get(1).performer);
        assertEquals("track1album", tracksDTO.tracks.get(0).album);
        assertEquals("track2album", tracksDTO.tracks.get(1).album);
        assertEquals(1, tracksDTO.tracks.get(0).id);
        assertEquals(2, tracksDTO.tracks.get(1).id);
        assertEquals(2, tracksDTO.tracks.size());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getTrackTest(){
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

    @Test
    public void getTracksByPlaylistTest(){
    //TODO
    }

    @Test
    public void deleteTrackFromPlaylistTest(){
    //TODO
    }

    @Test
    public void addTrackToPlaylistTest(){

    }

}
