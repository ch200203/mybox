package com.numble.mybox.mybox.folder.repository;

import com.numble.mybox.mybox.folder.domain.Folder;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FolderRepository {

    private final EntityManager em;

    public Optional<Folder> findFolderById(Long folderId) {
        Optional<Folder> folder = null;
        try {
            folder = Optional.ofNullable(em.createQuery(
                    "select f from Folder f where f.folderId = :folderId", Folder.class)
                .setParameter("folderId", folderId)
                .getSingleResult());
        } catch (NoResultException e) {
            folder = Optional.empty();
        } finally {
            return folder;
        }
    }

    public List<Folder> findFolderByParentId(Long parentId, Long userNumber) {
        return em.createQuery(
                "select f from Folder f "
                    + "where f.folderId =: parentId and f.user.userNumber = :userNumber",
                Folder.class)
            .setParameter("parentId", parentId)
            .setParameter("userNumber", userNumber)
            .getResultList();
    }

    public Optional<Folder> findFolderByIdWithChildren(Long folderId, Long userNumber) {
        String sql = "SELECT f FROM Folder f " +
            "LEFT JOIN FETCH f.childrenFolders " +
            "LEFT JOIN FETCH f.childrenFiles " +
            "WHERE f.folderId = :folderId ";
        Optional<Folder> folder = null;
        try {
            folder = Optional.ofNullable(em.createQuery(sql, Folder.class)
                .setParameter("folderId", folderId)
                .getSingleResult());
            return folder;
        } catch (NoResultException e) {
            return folder = Optional.empty();
        }
    }
}
