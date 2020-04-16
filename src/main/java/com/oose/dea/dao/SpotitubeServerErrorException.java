package com.oose.dea.dao;

import java.io.Serializable;

public class SpotitubeServerErrorException extends Exception implements Serializable {

    public SpotitubeServerErrorException() {
        super();
    }
    public SpotitubeServerErrorException(String msg)   {
        super(msg);
    }
    public SpotitubeServerErrorException(String msg, Exception e)  {
        super(msg, e);
    }
}
