package com.numble.mybox.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_number")
    private Long Id;

    @NotEmpty
    @Column(name = "user_id")
    private String userId;

    @NotEmpty
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_storage",columnDefinition = "BIGINT DEFAULT 30")
    private Double userStorage;

    @CreatedDate
    @Column(name = "user_reg_date")
    private LocalDateTime userRegDate;

}
