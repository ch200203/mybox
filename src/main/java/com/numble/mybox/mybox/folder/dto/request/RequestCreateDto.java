package com.numble.mybox.mybox.folder.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCreateDto {

    @NotNull
    private Long parentId;
    @NotNull
    private String folderName;

    private Long userNumber;
}
