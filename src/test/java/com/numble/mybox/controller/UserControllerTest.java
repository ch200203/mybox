package com.numble.mybox.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserController memberController;

    @Test
    void 유저_조회_테스트() {
        String path = "/api/v1/user/1";
    }

    private void assertOkResponse(ResultActions result) throws Exception {
        result.andExpect(status().isOk());
    }

    private void assertCreatedResponse(ResultActions result) throws Exception {
        result.andExpect(status().isCreated());
    }

}