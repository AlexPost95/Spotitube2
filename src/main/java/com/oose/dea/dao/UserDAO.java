package com.oose.dea.dao;

import com.oose.dea.domain.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO{

    @Resource(name = "jdbc/spotitube")
    DataSource dataSource;

    @Override
    public User getUserById(int userId){
        if (userId <= 0){
            return null;
        }

        User user = new User();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setToken(resultSet.getString("token"));
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public User addUser(String name, String password, String token){

        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into user(name, password, token) values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, token);

            User user = new User();
            int resultSet = preparedStatement.executeUpdate();

            return user;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
