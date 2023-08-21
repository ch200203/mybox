package com.numble.mybox.mybox.folder.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.numble.mybox.mybox.folder.domain.Folder;
import com.numble.mybox.mybox.folder.dto.request.RequestCreateDto;
import com.numble.mybox.mybox.folder.repository.FolderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(readOnly = true)
class FolderServiceTest {

    @InjectMocks
    private FolderService folderService;

    @MockBean
    private FolderRepository folderRepository;


    @Test
    void 폴더생성_테스트() {
        RequestCreateDto requestCreateDto = new RequestCreateDto();
        requestCreateDto.setFolderName("newFolder");
        requestCreateDto.setParentId(1L);
        requestCreateDto.setUserNumber(1L);

        folderService.createFolder(requestCreateDto);
    }


    @Test
    void 폴더명_중복_테스트() {
        Long parentId = 1L;
        Long userNumber = 1L;
        String folderName = "NewFolder";

        List<Folder> childFolders = new ArrayList<>();
        Folder childFolder1 = Folder.builder().folderName("Folder1").build();
        Folder childFolder2 = Folder.builder().folderName(folderName).build();
        Folder childFolder3 = Folder.builder().folderName("Folder3").build();
        childFolders.add(childFolder1);
        childFolders.add(childFolder2);
        childFolders.add(childFolder3);

        Folder parentFolder = Folder.builder()
            .folderId(2L)
            .childrenFolders(childFolders)
            .build();

        when(folderRepository.findFolderByParentId(parentId, userNumber))
            .thenReturn(Collections.singletonList(parentFolder));

        assertThatThrownBy(() -> folderService.validateCreateFolder(parentId, folderName, userNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Duplicate Folder Name Exception");
    }


}