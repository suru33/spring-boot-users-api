package com.suru.usersapi.controllers;

import com.suru.usersapi.domain.entities.User;
import com.suru.usersapi.exceptions.ApplicationException;
import com.suru.usersapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("TEST")
public class UserControllerTest {
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
    private final String mockUserJson1 = "{\n" +
            "    \"firstName\": \"Tom\",\n" +
            "    \"lastName\": \"Hayday\",\n" +
            "    \"phone\": \"+63-8969-236\",\n" +
            "    \"email\": \"tom@email.com\",\n" +
            "    \"addressLine1\": \"line-1\",\n" +
            "    \"addressLine2\": \"line-2\",\n" +
            "    \"city\": \"city\",\n" +
            "    \"state\": \"state\",\n" +
            "    \"zip\": \"zip\",\n" +
            "    \"countryCode\": \"DE\"\n" +
            "}";
    private final String mockUserJson2 = "{\n" +
            "    \"lastName\": \"Hayday\",\n" +
            "    \"phone\": \"+63-8969-236\",\n" +
            "    \"email\": \"tom@email.com\",\n" +
            "    \"addressLine2\": \"line-2\",\n" +
            "    \"city\": \"city\",\n" +
            "    \"state\": \"state\",\n" +
            "    \"countryCode\": \"DE\"\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUserHappyPath() throws Exception {
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockUserJson1)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String expected = "{id:1,firstName:Tom,lastName:Hayday,phone:+63-8969-236,email:tom@email.com," +
                "addressLine1:line-1,addressLine2:line-2,city:city,state:state,zip:zip,countryCode:CC}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals("Response Code", HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void createUserMissingFieldsInRequestBodyReturns400() throws Exception {
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockUserJson2)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals("Response Code", HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnValidEntity() throws Exception {
        Mockito.when(userService.getUserById(Mockito.any())).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String expected = "{id:1,firstName:Tom,lastName:Hayday,phone:+63-8969-236,email:tom@email.com," +
                "addressLine1:line-1,addressLine2:line-2,city:city,state:state,zip:zip,countryCode:CC}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals("Response Code", HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnBadRequestForNotExistingId() throws Exception {
        Mockito.when(userService.getUserById(Mockito.any())).thenThrow(new ApplicationException("ID not found"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/10")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals("Response Code", HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void updateUserByIdShouldReturn204() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockUserJson1)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals("Response Code", HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    public void deleteUserShouldReturn204() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals("Response Code", HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
