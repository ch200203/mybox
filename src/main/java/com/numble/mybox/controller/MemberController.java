package com.numble.mybox.controller;

import com.numble.mybox.entity.Member;
import com.numble.mybox.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/user/{id}")
    public Result findUser(@PathVariable("id") @Valid Long id) {
        Member member = memberService.findMember(id);
        MemberInfoResponseDto userInfoResponseDto = new MemberInfoResponseDto(
            member.getUserId(), member.getUserStorage(), member.getUserRegDate()
        );
        return new Result(200, userInfoResponseDto);
    }

    @PostMapping("/api/v1/user/create")
    public String createUser(@RequestBody @Valid CreateMemberRequestDto request) {
        Member member = new Member(request.getUserId(), request.getPassword());
        memberService.createMember(member);
        return "1";
    }

    @Data
    @AllArgsConstructor
    static class MemberInfoResponseDto {
        private String userId;
        private Double userStorage;
        private LocalDateTime userRegDate;
    }

    @Data
    @NoArgsConstructor
    static class CreateMemberRequestDto {
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
