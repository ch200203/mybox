package com.numble.mybox.mybox.folder.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestFindFolderDto {

    @NotNull
    private Long userNumber;

    @NotNull
    private Long folderId;

}
