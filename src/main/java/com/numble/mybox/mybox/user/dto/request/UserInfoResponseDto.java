package com.numble.mybox.mybox.user.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDto {
    private Long id;
    private String userId;
    private Double userStorage;
    private LocalDateTime userRegDate;
}
