package com.oose.dea.api;

import com.oose.dea.api.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.api.oose.dea.api.dto.TokenDTO;
import com.oose.dea.api.oose.dea.api.dto.TrackDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.dao.PlaylistDAO;
import com.oose.dea.dao.TrackDAO;
import com.oose.dea.domain.Playlist;
import com.oose.dea.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/*
* DONE:
* GET /playlists
* GET /playlists/:id
* POST /playlists
* DELETE /playlists/:id NOT DONE
*
* TODO GET /tracks
* TODO GET /playlists/:id/tracks
* TODO PUT /playlists/:id
* TODO DELETE /playlists/:id/tracks/:id
* TODO POST /playlists/:id/tracks
*
*
* */

@Path("")
public class Spotitube {

    private IPlaylistDAO iPlaylistDAO;
    private ITrackDAO iTrackDAO;

    @GET
    @Path("hello")
    public String hello() {
        return "Spotitube hello test";
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser() {

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.user = "alex";
        tokenDTO.token = "1234-1234-1234";

        return Response.status(200).entity(tokenDTO).build();
    }

    @GET
    @Path("playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@PathParam("id") int id){

        Playlist playlist = iPlaylistDAO.getPlaylistById(id);
        if (playlist == null) {
            return Response.status(404).build();
        }

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.id = playlist.getId();
        playlistDTO.name = playlist.getName();
        playlistDTO.owner = playlist.isOwner();
        playlistDTO.tracks = playlist.getTracks();

        return Response.status(200).entity(playlistDTO).build();
    }

    @GET
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(){

        ArrayList<Playlist> playlists = iPlaylistDAO.getPlaylists();

        if (playlists == null) {
            return Response.status(404).build();
        }

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = playlists.size();


        return Response.status(200).entity(playlistsDTO).build();
    }

    @DELETE
    @Path("playlists/{id}")
    public void deletePlaylist(@PathParam("id") int id) {

        iPlaylistDAO.deletePlaylistById(id);

    }

    @POST
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist() {

        String name = "new one";

        ArrayList<Playlist> playlists = iPlaylistDAO.addPlaylist(name);

        if (playlists == null) {
            return Response.status(404).build();
        }

        PlaylistDTO playlistDTO = new PlaylistDTO();


        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.playlists = playlists;
        playlistsDTO.length = playlists.size();

        return Response.status(200).entity(playlistsDTO).build();
    }

    @GET
    @Path("tracks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") int id){

        Track track = iTrackDAO.getTrackById(id);

        if (track == null) {
            return Response.status(404).build();
        }

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.id = track.getId();
        trackDTO.title = track.getTitle();
        trackDTO.performer = track.getPerformer();
        trackDTO.duration = track.getDuration();
        trackDTO.album = track.getAlbum();
        trackDTO.playount = track.getPlaycount();
        trackDTO.publicationDate = track.getPublicationDate();
        trackDTO.description = track.getDescription();
        trackDTO.offlineAvailable = track.getOfflineAvailable();


        return Response.status(200).entity(trackDTO).build();
    }

    @GET
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTracks(){

       return "GET TRACKS";
    }

        @Inject
    public void setPlaylistDAO(IPlaylistDAO iPlaylistDAO) {
        this.iPlaylistDAO = iPlaylistDAO;
    }

    @Inject
    public void setTrackDAO(ITrackDAO iTrackDAO) {
        this.iTrackDAO = iTrackDAO;
    }

}
