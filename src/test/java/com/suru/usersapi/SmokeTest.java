package com.suru.usersapi;

import com.suru.usersapi.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("TEST")
public class SmokeTest {

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() {
        assertNotNull(controller);
    }
}