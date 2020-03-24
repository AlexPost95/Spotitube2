package com.oose.dea.domain;

import java.util.UUID;

public class User {

        public String name;
        public String  password;
        public String token;

        public User(){};

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
}
