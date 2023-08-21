package com.numble.mybox.mybox.folder.domain;

import com.numble.mybox.mybox.file.domain.FileEntity;
import com.numble.mybox.mybox.user.domain.User;
import java.util.HashSet;
import java.util.Set;
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
import lombok.Builder;
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
    @JoinColumn(name = "user_number")
    private User user;

    @Column(name = "folder_name", length = 255)
    private String folderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_parent_id", referencedColumnName = "folder_id")
    private Folder parent;

    @Column(name = "folder_path")
    private String folderPath;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Folder> childrenFolders = new HashSet<>();

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<FileEntity> childrenFiles = new HashSet<>();

    @Builder
    public Folder(Long folderId, User user, String folderName, Folder parent, String folderPath, Set<Folder> childrenFolders) {
        this.folderId = folderId;
        this.user = user;
        this.folderName = folderName;
        this.parent = parent;
        this.folderPath = folderPath;
        this.childrenFolders = childrenFolders;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setParentFolder(Folder parent) {
        this.parent = parent;
    }

    public boolean hasSameFileName(String fileName) {
        return childrenFiles.stream()
            .anyMatch(f -> fileName.equals(f.getFileName()));
    }

    public boolean hasSameFolderName(String folderName) {
        return childrenFolders.stream()
            .anyMatch(f -> folderName.equals(f.getFolderName()));
    }

    public void createChildrenFile(FileEntity uploadFile) {
        childrenFiles.add(uploadFile);
    }
}
