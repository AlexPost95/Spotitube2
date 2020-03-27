package com.oose.dea.dao;

import com.oose.dea.domain.User;

public interface IUserDAO {

    /**
     * Returns a user based on a user id
     * @param userId the user id
     * @return User from database
     */
    User getUserById(int userId);

    /**
     * Adds a user to the database
     * @param name the name of the user
     * @param password the password of the user
     * @param token the token of the user
     * @return a User
     */
    User addUser(String name, String password, String token);
}
