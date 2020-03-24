package com.oose.dea.domain;

import java.util.UUID;

public class Token {

    public String user;
    public UUID token;

    public Token() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
