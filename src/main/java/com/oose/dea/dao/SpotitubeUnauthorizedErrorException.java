package com.oose.dea.dao;

import java.io.Serializable;

public class SpotitubeUnauthorizedErrorException extends Exception implements Serializable {

    public SpotitubeUnauthorizedErrorException() {
        super();
    }
    public SpotitubeUnauthorizedErrorException(String msg)   {
        super(msg);
    }
    public SpotitubeUnauthorizedErrorException(String msg, Exception e)  {
        super(msg, e);
    }
}

