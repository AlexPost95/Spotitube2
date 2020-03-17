package com.oose.dea.api;

import com.oose.dea.api.oose.dea.api.dto.PlaylistDTO;
import com.oose.dea.api.oose.dea.api.dto.PlaylistsDTO;
import com.oose.dea.api.oose.dea.api.dto.TokenDTO;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.domain.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("")
public class Spotitube {

    private IPlaylistDAO iPlaylistDAO;
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
    @Path("playlist/{id}")
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

    @Inject
    public void setPlaylistDAO(IPlaylistDAO iPlaylistDAO) {
        this.iPlaylistDAO = iPlaylistDAO;
    }

}
