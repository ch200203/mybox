package com.numble.mybox.mybox.file.repository;

import com.numble.mybox.mybox.file.domain.FileEntity;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public List<FileEntity> findUserFiles(Long id) {
        return em.createQuery("select f from FileEntity f where f.user.userNumber = :id", FileEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

    public void saveFile(FileEntity fileEntity) {
        em.persist(fileEntity);
    }

    public void deleteFile(Long fileId) {
        em.createQuery("delete from File where f.fileId = :fileId")
            .setParameter("fileId", fileId)
            .executeUpdate();
    }
}
