package com.oose.dea.api;

import com.oose.dea.api.dto.TokenDTO;
import com.oose.dea.dao.IUserDAO;
import com.oose.dea.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserApiTest {

    UserApi userApi;

    @BeforeEach
    public void setup(){
        userApi = new UserApi();
    }

    /**
     * Test the response when posting a new user
     */
    @Test
    public void loginTest(){
        IUserDAO userDAO = mock(IUserDAO.class);

        User user = new User();
        user.setName("testUser");
        user.setPassword("testPassword");
        user.setToken("testUserToken");

        userApi.setUserDAO(userDAO);

        Response response = userApi.getUser(user);

        TokenDTO tokenDTO = (TokenDTO)response.getEntity();

        assertEquals(user.getName(), tokenDTO.user);
    }
}
