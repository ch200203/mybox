package com.numble.mybox.mybox.user.service;

import com.numble.mybox.mybox.user.domain.User;
import com.numble.mybox.mybox.user.exception.UserNotFoundException;
import com.numble.mybox.mybox.user.repository.UserRepository;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Value("${path.upload-path}")
    private String FOLDER_PATH;

    public User findUser(Long id) {
        User user = userRepository.findUser(id);
        if(user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @Transactional
    public User createUser(User user) {
        validateDuplicateUser(user);
        userRepository.createUser(user);
        createRootFolder(user.getUserNumber());
        return userRepository.findUser(user.getUserNumber());
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findUsersById(user.getUserId());
        if(!findUsers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
    }

    private void createRootFolder(Long userNumber) {
        File directory = new File(FOLDER_PATH + File.separator + userNumber);
        if(!directory.exists()) {
            directory.mkdir();
        }

        if(!directory.exists()) {
            throw new IllegalArgumentException("Folder Create Exception");
        }
    }
}
