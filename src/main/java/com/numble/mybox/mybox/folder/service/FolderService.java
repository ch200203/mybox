package com.numble.mybox.mybox.folder.service;

import com.numble.mybox.mybox.file.domain.FileEntity;
import com.numble.mybox.mybox.folder.domain.Folder;
import com.numble.mybox.mybox.folder.dto.FolderDto;
import com.numble.mybox.mybox.folder.dto.request.RequestCreateDto;
import com.numble.mybox.mybox.folder.dto.request.RequestFindFolderDto;
import com.numble.mybox.mybox.folder.exception.FolderDuplicateNameException;
import com.numble.mybox.mybox.folder.exception.FolderNotFoundException;
import com.numble.mybox.mybox.folder.repository.FolderRepository;
import com.numble.mybox.mybox.user.domain.User;
import com.numble.mybox.mybox.user.repository.UserRepository;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    @Value("${path.upload-path}")
    private String FOLDER_PATH;

    public FolderDto findFolder(RequestFindFolderDto requestfindFolderDto) {
        Long folderId = requestfindFolderDto.getFolderId();
        Long userNumber = requestfindFolderDto.getUserNumber();
        Folder folder = folderRepository.findFolderByIdWithChildren(folderId, userNumber)
            .orElseThrow(() -> new IllegalArgumentException("Folder Not Found Exception"));
        return FolderDto.of(folder);
    }

    @Transactional
    public Long createFolder(RequestCreateDto requestCreateDto) {
        User user = userRepository.findUser(requestCreateDto.getUserNumber());

        validateCreateFolder(requestCreateDto.getParentId(), requestCreateDto.getFolderName(),
            user.getUserNumber());

        Folder parentFolder = null;
        if (requestCreateDto.getParentId() != null) {
            parentFolder = folderRepository.findFolderById(requestCreateDto.getParentId(),
                    requestCreateDto.getUserNumber())
                .orElseThrow(() -> new FolderNotFoundException(requestCreateDto.getParentId()));
        }

        String path = createPath(parentFolder.getFolderPath(), requestCreateDto.getFolderName());
        Folder newFolder = Folder.builder()
            .user(user)
            .folderName(requestCreateDto.getFolderName())
            .folderPath(path)
            .build();

        newFolder.setParentFolder(parentFolder);
        folderRepository.createFolder(newFolder);

        String fullPath =
            FOLDER_PATH + user.getUserNumber() + File.separator + path;
        createFolderInStorage(fullPath);

        return folderRepository.findFolderById(newFolder.getFolderId(),
            newFolder.getUser().getUserNumber()).get().getFolderId();
    }

    @Transactional
    public int deleteFolder(Long folderId, Long userNumber) {
        User user = userRepository.findUser(userNumber);
        Folder folder = folderRepository.findFolderById(folderId, userNumber).orElseThrow(
            () -> new FolderNotFoundException(folderId)
        );
        int result = folderRepository.deleteFolder(folderId);
        Double updateUserStorage = user.getUserStorage() + calculateTotalSize(folder);
        user.updateUserStorage(updateUserStorage);
        return result;
    }

    private String createPath(String path, String folderName) {
        return path + File.separator + folderName;
    }

    public void validateCreateFolder(Long parentId, String folderName, Long userNumber) {
        // 폴더명 중복오류
/*
        List<Folder> folders = folderRepository.findFolderByParentId(parentId, userNumber);
        folders.stream()
            .filter(f -> f.hasSameFolderName(folderName))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Duplicate Folder Name Exception"));
*/

        Folder folder = folderRepository.findFolderById(parentId, userNumber).orElseThrow(
            () -> new FolderNotFoundException(parentId));

        if (folder.hasSameFolderName(folderName)) {
            throw new FolderDuplicateNameException(folderName);
        }
    }

    private void createFolderInStorage(String path) {
        System.out.println("path : " + path);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (directory.exists()) {
            new IllegalArgumentException("Folder Not Create Exception");
        }
    }

    private Double calculateTotalSize(Folder folder) {
        Double totalSize = 0.0;
        // 현재 폴더 내의 파일들의 용량 계산
        for (FileEntity file : folder.getChildrenFiles()) {
            totalSize += file.getFileSize();
        }

        // 현재 폴더의 하위 폴더들에 대해 재귀적으로 용량 계산
        for (Folder childFolder : folder.getChildrenFolders()) {
            totalSize += calculateTotalSize(childFolder);
        }

        return totalSize;
    }

}
