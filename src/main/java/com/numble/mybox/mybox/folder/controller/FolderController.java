package com.numble.mybox.mybox.folder.controller;

import com.numble.mybox.mybox.folder.dto.FolderDto;
import com.numble.mybox.mybox.folder.dto.request.RequestDeleteDto;
import com.numble.mybox.mybox.folder.dto.request.RequestCreateDto;
import com.numble.mybox.mybox.folder.dto.request.RequestFindFolderDto;
import com.numble.mybox.mybox.folder.service.FolderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @GetMapping("/api/v1/folder")
    public ResponseEntity<?> findFolder(@RequestBody RequestFindFolderDto requestfindFolderDto) {
        FolderDto response = folderService.findFolder(requestfindFolderDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/api/v1/folder/create")
    public ResponseEntity<?> createFolder(@RequestBody @Valid RequestCreateDto requestCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(folderService.createFolder(requestCreateDto));
    }

    @DeleteMapping("/api/v1/folder/delete")
    public ResponseEntity<?> deleteFolder(RequestDeleteDto requestDeleteDto) {
        folderService.deleteFolder(requestDeleteDto.getFolderId(),
            requestDeleteDto.getUserNumber());
        return ResponseEntity.ok().build();
    }
}
