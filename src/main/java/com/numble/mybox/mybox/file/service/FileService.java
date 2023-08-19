package com.numble.mybox.mybox.file.service;

import com.numble.mybox.mybox.file.domain.FileEntity;
import com.numble.mybox.mybox.file.repository.FileRepository;
import com.numble.mybox.mybox.folder.domain.Folder;
import com.numble.mybox.mybox.folder.repository.FolderRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    private final String STORAGE_PATH = "/Users/ich/Downloads/myBox/storage/";

    public List<FileEntity> findUserFiles(Long id) {
        return fileRepository.findUserFiles(id);
    }

    @Transactional
    public void uploadFile(MultipartFile multipartFile, Long folderId) {
        Folder folder = folderRepository.findFolderById(folderId).orElseThrow(
            () -> new IllegalArgumentException("Folder Not Found")
        );

        String fileName = multipartFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        double fileSize = multipartFile.getSize();
        String filePath = STORAGE_PATH + folder.getFolderPath();

        validateUploadFile(folder, fileName);

        FileEntity uploadFile = FileEntity.builder()
            .fileName(fileName)
            .user(folder.getUser())
            .folder(folder)
            .filePath(filePath)
            .fileSize(fileSize)
            .fileExtension(fileExtension)
            .build();

        folder.createChildrenFile(uploadFile);
        saveFile(filePath, multipartFile);

        fileRepository.saveFile(uploadFile);
    }

    private void validateUploadFile(Folder parentFolder, String fileName) {
        // 중복되는 파일명 확인
        if (parentFolder.hasSameFileName(fileName)) {
            throw new IllegalArgumentException("Duplicate FileName");
        }

        // 세션 확인

        // 남아있는 용량 확인
    }

    private void saveFile(String filePath, MultipartFile multipartFile) {
        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
