package com.oose.dea;

import com.oose.dea.dao.TrackDAO;
import com.oose.dea.dao.UserDAO;
import com.oose.dea.domain.Track;
import com.oose.dea.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

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
        try {
            String expectedSQL = "select * from user where id = ?";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            userDAO.setDataSource(dataSource);
            int userId = 1;
            User user = userDAO.getUserById(userId);

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setInt(1, userId);
            verify(preparedStatement).executeQuery();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Test for adding a new user
     */
    @Test
    public void addUserTest(){
        try {
            String expectedSQL = "insert into user(name, password, token) values(?, ?, ?)";

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(resultSet.next()).thenReturn(false);

            userDAO.setDataSource(dataSource);
            String name = "user";
            String password = "password";
            String token = "token";
            userDAO.addUser(name, password, token);

            verify(dataSource).getConnection();
            verify(connection).prepareStatement(expectedSQL);
            verify(preparedStatement).setString(1, name);
            verify(preparedStatement).setString(2, password);
            verify(preparedStatement).setString(3, token);
            verify(preparedStatement).executeUpdate();

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

}
