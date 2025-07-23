package com.example.jarvis;


import com.example.jarvis.controller.UserDataController;
import com.example.jarvis.entity.UserData;
import com.example.jarvis.model.IdRequest;
import com.example.jarvis.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserDataController.class)
public class UserDataControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetUserByPhoneNumber_Success() throws Exception {
        // Mock AadharData object
        UserData mockUserData = new UserData();
        mockUserData.setPhoneNumber("123456789012");
        mockUserData.setProfession("Software Engineer");
        mockUserData.setAccountNumber("123456");
        mockUserData.setIfscCode("SBIN00");
        mockUserData.setIncome("90,00,000");

        // Mock request
        IdRequest request = new IdRequest();
        request.setPhoneNumber("123456789012");


        when(userService.getUserDataByPhoneNumber("123456789012"))
                .thenReturn(mockUserData);

        mockMvc.perform(post("/lbgUserDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("123456789012"))
                .andExpect(jsonPath("$.profession").value("Software Engineer"))
                .andExpect(jsonPath("$.accountNumber").value("123456"));
    }

    @Test
    void testGetUserByPhoneNumber_NotFound() throws Exception {
        IdRequest request = new IdRequest();
        request.setPhoneNumber("1234567890122");


        when(userService.getUserDataByPhoneNumber("1234567890122"))
                .thenReturn(null);

        mockMvc.perform(post("/lbgUserDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No user with that phone number:1234567890122"));
    }


}


