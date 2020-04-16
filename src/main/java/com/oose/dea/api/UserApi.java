package com.oose.dea.api;

import com.oose.dea.api.dto.TokenDTO;
import com.oose.dea.dao.IUserDAO;
import com.oose.dea.domain.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("")
public class UserApi {

    public IUserDAO iUserDAO;

    /**
     * Log a user in
     * @param user the user that is sent within the body of the request
     * @return return a response code and a user token
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(User user) {

        String generatedToken = UUID.randomUUID().toString();

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.user = user.getName();
        tokenDTO.token = generatedToken;

        iUserDAO.addUser(user.getName(), user.getPassword(), generatedToken);

        return Response.status(200).entity(tokenDTO).build();
    }

    /**
     * Set a IUserDAO
     * @param iUserDAO
     */
    @Inject
    public void setUserDAO(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }
}
