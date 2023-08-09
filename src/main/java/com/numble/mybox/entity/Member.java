package com.numble.mybox.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "user_number")
    private Long Id;

    @NotEmpty
    @Column(name = "user_id")
    private String memberId;

    @NotEmpty
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_storage",columnDefinition = "BIGINT DEFAULT 30")
    private Double memberUsedStorage;

    @CreatedDate
    @Column(name = "user_reg_date")
    private LocalDateTime memberRegDate;

    @Builder
    public Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

}
