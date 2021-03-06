package com.oose.dea.api.exceptionmappers;

import com.oose.dea.dao.SpotitubeServerErrorException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SpotitubeServerErrorExceptionMapper implements ExceptionMapper<SpotitubeServerErrorException> {

    /**
     * ExceptionMapper for server errors
     * @param exception
     * @return
     */
    @Override
    public Response toResponse(SpotitubeServerErrorException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
