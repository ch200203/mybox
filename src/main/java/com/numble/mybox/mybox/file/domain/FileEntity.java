package com.numble.mybox.mybox.file.domain;

import com.numble.mybox.mybox.folder.domain.Folder;
import com.numble.mybox.mybox.user.domain.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_number", referencedColumnName = "user_number") // 외래 키 설정
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", referencedColumnName = "folder_id", nullable = true)
    private Folder folder;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_storage", nullable = false)
    private Double fileSize;

    @Column(name = "file_extension", length = 10)
    private String fileExtension;

    @Column(name = "file_reg_date")
    @CreationTimestamp
    private LocalDateTime fileRegDate;

    @Builder
    public FileEntity(String fileName, User user, Folder folder, String filePath, Double fileSize,
        String fileExtension) {
        this.fileName = fileName;
        this.user = user;
        this.folder = folder;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
    }
}
