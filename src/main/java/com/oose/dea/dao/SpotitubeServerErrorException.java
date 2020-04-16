package com.oose.dea.dao;

import java.io.Serializable;

public class SpotitubeServerErrorException extends Exception implements Serializable {

    public SpotitubeServerErrorException(String msg)   {
        super(msg);
    }

}
