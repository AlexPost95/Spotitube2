package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import java.util.ArrayList;

public interface IPlaylistDAO {
    Playlist getPlaylistById(int playlistId);
    ArrayList<Playlist> getPlaylists();
    ArrayList<Playlist> deletePlaylistById(int id);
    ArrayList<Playlist> addPlaylist(String name);
    int getTotalDuration();
    ArrayList<Playlist> updatePlaylistById(int playlistId, String name);
    ArrayList<Track> getTracksByPlaylistId(int playlistId);
    ArrayList<Track> deleteSongFromPlaylist(int playlistId, int trackId);
    ArrayList<Track> getAllTracks();
}
