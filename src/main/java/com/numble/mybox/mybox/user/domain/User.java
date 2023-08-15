package com.numble.mybox.mybox.user.domain;

import com.numble.mybox.mybox.file.domain.FileEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long userNumber;

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

    @OneToMany
    @JoinColumn(name = "file_id")
    private List<FileEntity> userFileListEntity = new ArrayList<>();

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
