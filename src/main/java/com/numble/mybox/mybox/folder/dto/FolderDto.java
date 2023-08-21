package com.numble.mybox.mybox.folder.dto;

import com.numble.mybox.mybox.folder.domain.Folder;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FolderDto {
    private Long folderId;
    private String folderName;
    private List<FolderDto> childFolders;
    private List<FileDto> childFiles;

    public static FolderDto of(Folder folder) {
        return FolderDto.builder()
            .folderId(folder.getFolderId())
            .folderName(folder.getFolderName())
            .childFolders(folder.getChildrenFolders().stream()
                .map(FolderDto::of)
                .collect(Collectors.toList()))
            .childFiles(folder.getChildrenFiles().stream()
                .map(FileDto::new)
                .collect(Collectors.toList()))
            .build();
    }
}
