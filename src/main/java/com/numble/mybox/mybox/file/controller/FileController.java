package com.numble.mybox.mybox.file.controller;

import com.numble.mybox.mybox.file.service.FileService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/v1/file/{id}")
    public ResponseEntity<?> findUserFiles(@PathVariable("id") @Valid Long id) {
        return ResponseEntity.ok(fileService.findUserFiles(id));
    }


}
