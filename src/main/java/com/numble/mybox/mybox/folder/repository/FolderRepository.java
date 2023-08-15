package com.numble.mybox.mybox.folder.repository;

import com.numble.mybox.mybox.folder.domain.Folder;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FolderRepository {

    private final EntityManager em;

    public Optional<Folder> findFolderById(Long folderId) {
        return Optional.ofNullable(em.createQuery(
                "select f from folder f where f.folderId = :folderId", Folder.class)
            .setParameter("folderId", Folder.class)
            .getSingleResult());
    }

}
