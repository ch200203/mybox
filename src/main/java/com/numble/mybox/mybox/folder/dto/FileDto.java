package com.numble.mybox.mybox.folder.dto;

import com.numble.mybox.mybox.file.domain.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDto {

    private Long fileId;
    private String fileName;
    private String filePath;
    private String fileExtension;
    private Double fileSize;

    public FileDto(FileEntity fileEntity) {
        this.fileId = fileEntity.getFileId();
        this.fileName = fileEntity.getFileName();
        this.filePath = fileEntity.getFilePath();
        this.fileExtension = fileEntity.getFileExtension();
        this.fileSize = fileEntity.getFileSize();
    }

}
