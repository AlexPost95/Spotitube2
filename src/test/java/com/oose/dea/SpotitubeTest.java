package com.oose.dea;

import com.oose.dea.api.Spotitube;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpotitubeTest {

    Spotitube spotitube;

    @BeforeEach
    public void setup(){
        spotitube = new Spotitube();
    }

    @Test
    public void helloTest(){
        String expected = "hello test";
        assertEquals(expected, spotitube.hello());
    }

}
