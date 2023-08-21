package com.numble.mybox.mybox.folder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FolderExceptionHandler {

    @ExceptionHandler(FolderNotFoundException.class)
    public ResponseEntity<?> handlerFolderNotFoundException(FolderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FolderDuplicateNameException.class)
    public ResponseEntity<?> handlerFolderDuplicateNameException(FolderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
