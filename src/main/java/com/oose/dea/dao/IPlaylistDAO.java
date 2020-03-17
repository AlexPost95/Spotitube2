package com.oose.dea.dao;

import com.oose.dea.domain.Playlist;

import java.util.ArrayList;

public interface IPlaylistDAO {
    Playlist getPlaylistById(int playlistId);
    ArrayList<Playlist> getPlaylists();
    void deletePlaylistById(int id);
    ArrayList<Playlist> addPlaylist(String name);

}
