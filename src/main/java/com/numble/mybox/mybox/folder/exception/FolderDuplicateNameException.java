package com.numble.mybox.mybox.folder.exception;

public class FolderDuplicateNameException extends RuntimeException {

    public FolderDuplicateNameException(String folderName) {
        super("Duplicate Folder Name Exception : " + folderName);
    }

}
