package com.numble.mybox.mybox.user.service;

import com.numble.mybox.mybox.user.exception.UserNotFoundException;
import com.numble.mybox.mybox.user.domain.User;
import com.numble.mybox.mybox.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(Long id) {
        if(userRepository.findUser(id) == null) {
            throw new UserNotFoundException(id);
        }
        return userRepository.findUser(id);
    }

    @Transactional
    public User createUser(User user) {
        validateDuplicateUser(user);
        userRepository.createUser(user);
        return userRepository.findUser(user.getId());
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findUsersById(user.getUserId());
        if(!findUsers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
    }
}