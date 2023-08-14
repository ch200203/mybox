package com.numble.mybox.mybox.file.repository;

import com.numble.mybox.mybox.file.domain.File;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public List<File> findUserFiles(Long id) {
        return em.createQuery("select f from File f where f.user.userNumber = :id", File.class)
            .setParameter("id", id)
            .getResultList();
    }
}
