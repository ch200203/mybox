package com.numble.mybox.service;

import com.numble.mybox.entity.User;
import com.numble.mybox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(Long id) {
        return userRepository.findUser(id);
    }
}
