package com.oose.dea.api.exceptionmappers;

import com.oose.dea.dao.SpotitubeUnauthorizedErrorException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SpotitubeUnauthorizedErrorExceptionMapper implements ExceptionMapper<SpotitubeUnauthorizedErrorException> {

    @Override
    /**
     * ExceptionMapper for unauthorized errors
     * @param exception
     * @return
     */
    public Response toResponse(SpotitubeUnauthorizedErrorException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
    }
}
