package com.oose.dea.dao;

import java.io.Serializable;

public class SpotitubeUnauthorizedErrorException extends Exception implements Serializable {

    public SpotitubeUnauthorizedErrorException(String msg)   {
        super(msg);
    }
}

