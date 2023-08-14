package com.numble.mybox.user.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.numble.mybox.mybox.user.service.UserService;
import com.numble.mybox.mybox.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
class UserServiceTest {

    @Autowired
    private UserService userService;
    
    @Test
    void 유저조회_1() throws Exception {
        //given
        Long user_id = 1L;

        //when
        User user = userService.findUser(user_id);
        Double expectedUserStorage = user.getUserStorage();

        //then
        assertEquals(30, expectedUserStorage);
    }

}