package com.oose.dea.api;

import com.oose.dea.api.oose.dea.api.dto.*;
import com.oose.dea.dao.IPlaylistDAO;
import com.oose.dea.dao.ITrackDAO;
import com.oose.dea.dao.IUserDAO;
import com.oose.dea.domain.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

/**
 * REST Api
 */
@Path("")
public class Spotitube {

    private IPlaylistDAO iPlaylistDAO;
    private ITrackDAO iTrackDAO;
    public IUserDAO iUserDAO;

    /**
     * Print a string when accessing /hello. Just to test if the program is running
     * @return
     */
    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {

        return "hello test";
    }


}
