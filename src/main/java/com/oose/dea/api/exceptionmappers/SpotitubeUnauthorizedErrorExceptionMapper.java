package com.oose.dea.api.exceptionmappers;

import com.oose.dea.dao.SpotitubeServerErrorException;
import com.oose.dea.dao.SpotitubeUnauthorizedErrorException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class SpotitubeUnauthorizedErrorExceptionMapper implements ExceptionMapper<SpotitubeUnauthorizedErrorException> {

    @Override
    public Response toResponse(SpotitubeUnauthorizedErrorException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
    }
}
