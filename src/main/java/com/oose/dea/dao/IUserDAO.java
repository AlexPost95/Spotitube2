package com.oose.dea.dao;

import com.oose.dea.domain.User;

public interface IUserDAO {

    User getUserById(int userId);
    User addUser(String token);
}
