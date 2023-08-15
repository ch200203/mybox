package com.numble.mybox.mybox.file.controller;

import com.numble.mybox.mybox.file.service.FileService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/api/v1/file/upload/{folderId}")
    public ResponseEntity<?> uploadFile(@RequestParam(required = false) MultipartFile file,
        @PathVariable Long folderId) {
        fileService.uploadFile(file, folderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(folderId);
    }

}
