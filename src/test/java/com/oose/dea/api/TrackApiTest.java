package com.oose.dea.api;

import com.oose.dea.api.dto.TrackDTO;
import com.oose.dea.api.dto.TracksDTO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackApiTest {
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
     * Test the response when retrieving tracks
     */
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

        when(trackDAO.getTracks(1)).thenReturn(tracks);

        trackApi.setTrackDAO(trackDAO);

        Response response = trackApi.getTracks("token", 1);

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
     * Test the response when retrieving a specific track
     */
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

        trackApi.setTrackDAO(trackDAO);

        Response response = trackApi.getTrack("token", 1);

        TrackDTO trackDTO = (TrackDTO)response.getEntity();

        assertEquals("Still Into You", trackDTO.title);
        assertEquals("Paramore", trackDTO.performer);
        assertEquals("Paramore", trackDTO.album);
        assertEquals(339, trackDTO.duration);
        assertEquals(1, trackDTO.id);
        assertEquals(200, response.getStatus());
    }

}
