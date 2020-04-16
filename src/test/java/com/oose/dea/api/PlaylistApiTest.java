package com.oose.dea.api;

import com.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.api.dto.TracksDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Playlist2;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistApiTest {

    UserApi userApi;
    TrackApi trackApi;
    PlaylistApi playlistApi;

    @BeforeEach
    public void setup(){
        userApi = new UserApi();
        trackApi = new TrackApi();
        playlistApi = new PlaylistApi();
    }

    /**
     * Test the response when retrieving all playlists
     */
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

        when(playlistDAO.getPlaylists("token")).thenReturn(playlists);

        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.getPlaylists("token");

        PlaylistsDTO playlistsDTO = (PlaylistsDTO)response.getEntity();
        assertEquals("First playlist", playlistsDTO.playlists.get(0).name);
        assertEquals("Second playlist", playlistsDTO.playlists.get(1).name);
        assertEquals(1, playlistsDTO.playlists.get(0).id);
        assertEquals(2, playlistsDTO.playlists.get(1).id);
        assertEquals(2, playlistsDTO.playlists.size());
        assertEquals(200, response.getStatus());
    }

    /**
     * Test the response when retrieving all playlists
     */
    @Test
    public void getPlaylistTest(){
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("First playlist");
        playlist.setTracks("");

        when(playlistDAO.getPlaylistById(1, "token")).thenReturn(playlist);

        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.getPlaylist("token", 1);

        PlaylistDTO playlistDTO = (PlaylistDTO)response.getEntity();

        assertEquals("First playlist", playlistDTO.name);
        assertEquals(1, playlistDTO.id);
        assertEquals(200, response.getStatus());
    }

    /**
     * Test the response when deleting a playlist
     */
    @Test
    public void deletePlaylistTest(){
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

        when(playlistDAO.getPlaylists("token")).thenReturn(playlists);

        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.deletePlaylist("token", 1);

        PlaylistsDTO playlistsDTO = (PlaylistsDTO)response.getEntity();
        assertEquals("First playlist", playlistsDTO.playlists.get(0).name);
        assertEquals("Second playlist", playlistsDTO.playlists.get(1).name);
        assertEquals(2, playlistsDTO.playlists.size());
        assertEquals(200, response.getStatus());
    }

    /**
     * Test the response when adding a playlist
     */
    @Test
    public void addPlaylistTest(){
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

        Playlist2 newPlaylist = new Playlist2();
        newPlaylist.setName("new playlist");
        newPlaylist.setOwner(true);

        when(playlistDAO.getPlaylists("token")).thenReturn(playlists);

        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.addPlaylist("token", newPlaylist);

        PlaylistsDTO playlistsDTO = (PlaylistsDTO)response.getEntity();
        assertEquals("First playlist", playlistsDTO.playlists.get(0).name);
        assertEquals("Second playlist", playlistsDTO.playlists.get(1).name);
        assertEquals(2, playlistsDTO.playlists.size());
        assertEquals(201, response.getStatus());
    }

    /**
     * Test the response when updating a playlist
     */
    @Test
    public void updatePlaylistTest(){
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

        when(playlistDAO.getPlaylists("token")).thenReturn(playlists);

        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.updatePlaylist("token", playlist1);

        PlaylistsDTO playlistsDTO = (PlaylistsDTO)response.getEntity();
        assertEquals("First playlist", playlistsDTO.playlists.get(0).name);
        assertEquals("Second playlist", playlistsDTO.playlists.get(1).name);
        assertEquals(2, playlistsDTO.playlists.size());
        assertEquals(200, response.getStatus());
    }

    /**
     * Test the response when retrieving all tracks that belong to a specific playlist
     */
    @Test
    public void getTracksByPlaylistTest(){
        ITrackDAO trackDAO = mock(ITrackDAO.class);
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

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

        when(playlistDAO.getTracksByPlaylistId(1, "token")).thenReturn(tracks);

        trackApi.setTrackDAO(trackDAO);
        playlistApi.setPlaylistDAO(playlistDAO);

        Response response = playlistApi.getTracksByPlaylist("token", 1);

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

    /**
     * Test the response when deleting a track from a playlist
     */
    @Test
    public void deleteTrackFromPlaylistTest(){
        ITrackDAO trackDAO = mock(ITrackDAO.class);
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

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

        when(playlistDAO.getAllTracks("token")).thenReturn(tracks);

        trackApi.setTrackDAO(trackDAO);
        playlistApi.setPlaylistDAO((playlistDAO));

        Response response = playlistApi.deleteTrackFromPlaylist("token", 1, 1);

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

    /**
     * Test the response when adding a track to a playlist
     */
    @Test
    public void addTrackToPlaylistTest(){

        ITrackDAO trackDAO = mock(ITrackDAO.class);
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

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

        when(playlistDAO.getTracksByPlaylistId(1, "token")).thenReturn(tracks);

        trackApi.setTrackDAO(trackDAO);
        playlistApi.setPlaylistDAO((playlistDAO));

        Response response = playlistApi.addTrackToPlaylist("token", 1, track1);

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
        assertEquals(201, response.getStatus());
    }
}
