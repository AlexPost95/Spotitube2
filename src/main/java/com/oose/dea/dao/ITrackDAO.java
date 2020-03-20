package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import java.util.ArrayList;

public interface ITrackDAO {
    Track getTrackById(int trackId);
    ArrayList<Track> getTrackByPlaylistId(int playlistId);
    ArrayList<Track> getTracks();
}
