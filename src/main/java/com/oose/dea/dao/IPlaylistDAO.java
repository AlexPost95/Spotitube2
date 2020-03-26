package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import java.util.ArrayList;

public interface IPlaylistDAO {
    Playlist getPlaylistById(int playlistId, String owner);
    ArrayList<Playlist> getPlaylists(String owner);
    ArrayList<Playlist> deletePlaylistById(int id, String owner);
    ArrayList<Playlist> addPlaylist(String name, String owner);
    int getTotalDuration(String owner);
    ArrayList<Playlist> updatePlaylistById(int playlistId, String name, String owner);
    ArrayList<Track> getTracksByPlaylistId(int playlistId, String owner);
    ArrayList<Track> deleteSongFromPlaylist(int playlistId, int trackId, String owner);
    ArrayList<Track> getAllTracks(String owner);
    ArrayList<Track> addTrackToPlaylist(int playlistId, int trackId, String owner);
}
