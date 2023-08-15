package com.numble.mybox.mybox.folder.domain;

import com.numble.mybox.mybox.file.domain.FileEntity;
import com.numble.mybox.mybox.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long folderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "folder_name", nullable = false, length = 255)
    private String folderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_parent_id", referencedColumnName = "folder_id")
    private Folder parent;

    @Column(name = "folder_path")
    private String folderPath;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> childrenFolders = new ArrayList<>();

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> childrenFiles = new ArrayList<>();

    public boolean hasSameFileName(String folderName) {
        return childrenFiles.stream()
            .filter(f -> folderName.equals(f.getFileName()))
            .findAny()
            .isPresent();
    }

    public void createChildrenFile(FileEntity uploadFile) {
        childrenFiles.add(uploadFile);
    }
}
