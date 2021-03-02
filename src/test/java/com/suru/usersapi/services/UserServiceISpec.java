package com.suru.usersapi.services;

import com.suru.usersapi.domain.UserRequest;
import com.suru.usersapi.domain.entities.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("TEST")
public class UserServiceISpec {

    @Autowired
    private Flyway flyway;

    @Autowired
    private UserService service;
    @Autowired
    private ModelMapper modelMapper;

    private final UserRequest mockUserRequest = new UserRequest(
            "Tom",
            "Hayday",
            "+63-8969-236",
            "tom@email.com",
            "line-1",
            "line-2",
            "city",
            "state",
            "zip",
            "CC"
    );
    private final User mockUser = new User(
            1L,
            "Tom",
            "Hayday",
            "+63-8969-236",
            "tom@email.com",
            "line-1",
            "line-2",
            "city",
            "state",
            "zip",
            "CC"
    );

    @BeforeEach
    public void setup() {
        flyway.migrate();
    }

    @AfterEach
    public void clean() {
        flyway.clean();
    }

    @Test
    public void createUserShouldCreateEntity() throws Exception {
        User user = service.createUser(mockUserRequest);
        assertEquals(user, mockUser, "Created user");
    }

    @Test
    public void createUserShouldThrowExceptionForUniqueFields() throws Exception {
        service.createUser(mockUserRequest);

        UserRequest duplicateRequest = mockUserRequest.withFirstName("Hello").withLastName("Last Name");
        Exception exception = assertThrows(Exception.class, () -> service.createUser(duplicateRequest));

        assertNotNull(exception);
    }

    @Test
    public void updateUserShouldUpdateEntity() throws Exception {
        User user = service.createUser(mockUserRequest);

        UserRequest userRequest = modelMapper.map(user, UserRequest.class);
        String newEmail = "newemail@email.com";
        userRequest.setEmail(newEmail);
        service.updateUser(user.getId(), userRequest);

        User userById = service.getUserById(user.getId());

        assertEquals(userById.getEmail(), newEmail, "Updated user");
    }
}
