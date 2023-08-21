package com.numble.mybox.mybox.folder.exception;

public class FolderNotFoundException extends RuntimeException {

    public FolderNotFoundException(Long folderId) {
        super("Folder Id " + folderId + " Not found.");
    }

}
