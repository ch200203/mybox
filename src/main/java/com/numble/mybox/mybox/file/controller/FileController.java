package com.numble.mybox.mybox.file.controller;

import com.numble.mybox.mybox.file.service.FileService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/v1/file/{id}")
    public ResponseEntity<?> findUserFiles(@PathVariable("id") @Valid Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.findUserFiles(id));
    }

    @PostMapping("/api/v1/file/upload")
    public ResponseEntity<?> uploadFile(@RequestBody UploadFileRequestDto uploadFileRequestDto) {
        fileService.uploadFile(uploadFileRequestDto.getFile(), uploadFileRequestDto.getFolderId(),
            uploadFileRequestDto.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/api/v1/file/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileId") @Valid Long fileId) {
        // fileService.downloadFile(fileId);
        return null;
    }

    @DeleteMapping("/api/v1/delete/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable("fileId") @Valid Long fileId) {
        fileService.deleteFile(fileId);
        return null;
    }

    @Data
    static class UploadFileRequestDto {

        @NotEmpty
        private MultipartFile file;
        @NotNull
        private Long folderId;
        @NotNull
        private Long userId;
    }

}
