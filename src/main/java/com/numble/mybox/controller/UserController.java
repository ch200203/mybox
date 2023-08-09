package com.numble.mybox.controller;

import com.numble.mybox.entity.User;
import com.numble.mybox.service.UserService;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/user/{id}")
    public Result findUser(@PathVariable("id") @Valid Long id) {
        User user = userService.findUser(id);
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(
            user.getUserId(), user.getUserStorage(), user.getUserRegDate()
        );
        return new Result(200, userInfoResponseDto);
    }

    @PostMapping("/api/v1/user/create")
    public String createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        return "1";
    }

    @Data
    @AllArgsConstructor
    static class UserInfoResponseDto {
        private String userId;
        private Double userStorage;
        private LocalDateTime userRegDate;
    }

    @Data
    @NoArgsConstructor
    static class CreateUserRequestDto {
        private String userId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int response;
        private T data;
    }

}
