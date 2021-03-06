package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import java.util.ArrayList;

public interface ITrackDAO {

    /**
     * Get a specific track
     * @param trackId the id of the track
     * @return the track with given id
     * @throws SpotitubeServerErrorException
     */
    Track getTrackById(int trackId) throws SpotitubeServerErrorException;

    /**
     * Get all tracks that could be added to playlist with id forPlaylist
     * @param forPlaylist the id of the playlist that wants to add tracks to it
     * @return a list of tracks that could be added to the given playlist
     * @throws SpotitubeServerErrorException
     */
    ArrayList<Track> getTracks(int forPlaylist) throws SpotitubeServerErrorException;
}
