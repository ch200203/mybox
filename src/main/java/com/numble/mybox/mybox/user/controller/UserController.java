package com.numble.mybox.mybox.user.controller;

import com.numble.mybox.mybox.user.domain.User;
import com.numble.mybox.mybox.user.service.UserService;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/user/{id}")
    public Result findUser(@PathVariable("id") @Valid Long id) {
        User user = userService.findUser(id);
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(
            user.getUserNumber(), user.getUserId(), user.getUserStorage(), user.getUserRegDate()
        );
        return new Result(HttpStatus.OK, userInfoResponseDto);
    }

    @PostMapping("/v1/user/create")
    public Result createUser(@RequestBody @Valid CreateUserRequestDto requestDto) {
        User user = new User(requestDto.getUserId(), requestDto.getPassword());
        User currentUser = userService.createUser(user);
        return new Result(HttpStatus.CREATED, new CreateUserResponseDto(currentUser.getUserNumber()));
    }

    @Data
    @AllArgsConstructor
    static class UserInfoResponseDto {
        private Long userNumber;
        private String userId;
        private Double userStorage;
        private LocalDateTime userRegDate;
    }

    @Data
    @NoArgsConstructor
    static class CreateUserRequestDto {
        @NotEmpty
        private String userId;
        @NotEmpty
        private String password;
    }

    @Data
    @NoArgsConstructor
    static class CreateUserResponseDto {
        private Long id;

        public CreateUserResponseDto(Long id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {

        private HttpStatus status;
        private T data;
    }

}
