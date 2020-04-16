package com.oose.dea.api.exceptionmappers;

import com.oose.dea.dao.SpotitubeServerErrorException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SpotitubeServerErrorExceptionMapper implements ExceptionMapper<SpotitubeServerErrorException> {

    @Override
    public Response toResponse(SpotitubeServerErrorException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
