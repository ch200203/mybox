package com.numble.mybox.mybox.folder.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDeleteDto {

    @NotNull
    private Long folderId;

    @NotNull
    private Long userNumber;
}
