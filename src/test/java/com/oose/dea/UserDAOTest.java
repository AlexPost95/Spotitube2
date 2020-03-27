package com.oose.dea;

import com.oose.dea.dao.TrackDAO;
import com.oose.dea.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.mock;

public class UserDAOTest {

    UserDAO userDAO;
    DataSource dataSource;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    /**
     * Generating mocks that every test can use
     */
    @BeforeEach
    public void setup(){
        userDAO = new UserDAO();
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
    }

    /**
     * Test for getting a specific user
     */
    @Test
    public void getUserByIdTest(){

    }

    /**
     * Test for adding a new user
     */
    @Test
    public void addUserTest(){

    }

}
