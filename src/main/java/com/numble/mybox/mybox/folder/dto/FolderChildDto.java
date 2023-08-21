package com.numble.mybox.mybox.folder.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FolderChildDto {

    private List<FolderDto> folderDtoList;
    private List<FileDto> fileDtoList;
}
