package com.numble.mybox.mybox.file.service;

import com.numble.mybox.mybox.file.domain.File;
import com.numble.mybox.mybox.file.repository.FileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository fileRepository;

    public List<File> findUserFiles(Long id) {
        return fileRepository.findUserFiles(id);
    }
}
