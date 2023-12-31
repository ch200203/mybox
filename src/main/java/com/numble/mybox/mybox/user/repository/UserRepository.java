package com.numble.mybox.mybox.user.repository;

import com.numble.mybox.mybox.user.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findUser(Long userNumber) {
        return em.find(User.class, userNumber);
        /*return em.createQuery(
                "select u from User u where u.userNumber = :userNumber", User.class)
            .setParameter("userNumber", userNumber)
            .getSingleResult();*/
    }

    public List<User> findUsersById(String userId) {
        return em.createQuery(
                "select u from User u where u.userId = :userId"
                , User.class)
            .setParameter("userId", userId)
            .getResultList();
    }

    public void createUser(User user) {
        em.persist(user);
    }
}
