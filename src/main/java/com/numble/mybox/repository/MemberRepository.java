package com.numble.mybox.repository;

import com.numble.mybox.entity.Member;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member findUser(Long id) {
        return em.find(Member.class, id);
    }
}
