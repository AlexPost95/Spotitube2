package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import java.util.ArrayList;

public interface IPlaylistDAO {

    /**
     * Get a playlist from the database with a specific id
     * @param playlistId the id of the playlist
     * @param owner the token of the owner
     * @return a Playlist
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    Playlist getPlaylistById(int playlistId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Get all playlists from the database
     * @param owner the token of the owner
     * @return a list of all playlists
     * @throws SpotitubeUnauthorizedErrorException
     */
    ArrayList<Playlist> getPlaylists(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Delete a playlist with a specific id
     * @param id the id of the playlist that needs to be deleted
     * @param owner the token of the owner
     * @return a list of all updated playlists
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    void deletePlaylistById(int id, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Add a new playlist
     * @param name the name of the playlist
     * @param owner the token of the owner
     * @return a list of all updated playlists
     * @throws SpotitubeServerErrorException
     */
    void addPlaylist(String name, String owner) throws SpotitubeUnauthorizedErrorException;

    /**
     * Get the total duration of all tracks that are in a playlist
     * @param owner the token of the owner
     * @return the total duration
     * @throws SpotitubeUnauthorizedErrorException
     */
    int getTotalDuration(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Update the name of a playlist
     * @param playlistId the id of the playlist
     * @param name the new name of the playlist
     * @param owner the token of the owner
     * @return a list of all updated playlists
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    void updatePlaylistById(int playlistId, String name, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Get all tracks that are part of a specific playlist
     * @param playlistId the id of the playlist
     * @param owner the token of the owner
     * @return a list of tracks
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    ArrayList<Track> getTracksByPlaylistId(int playlistId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Delete a track from a playlist
     * @param playlistId the id of the playlist
     * @param trackId the id of the track that needs to be deleted
     * @param owner the token of the owner
     * @return a list of tracks
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    void deleteTrackFromPlaylist(int playlistId, int trackId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Get all tracks
     * @param owner the token of the owner
     * @return a list of tracks
     * @throws SpotitubeUnauthorizedErrorException
     */
    ArrayList<Track> getAllTracks(String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;

    /**
     * Add a track to a playlist
     * @param playlistId the id of the playlist
     * @param trackId the id of the track
     * @param owner the token of the owner
     * @return a list of tracks
     * @throws SpotitubeUnauthorizedErrorException
     * @throws SpotitubeServerErrorException
     */
    void addTrackToPlaylist(int playlistId, int trackId, String owner) throws SpotitubeUnauthorizedErrorException, SpotitubeServerErrorException;
}
