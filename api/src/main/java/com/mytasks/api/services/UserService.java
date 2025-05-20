package com.mytasks.api.services;

import com.mytasks.api.dtos.UserRecordDto;
import com.mytasks.api.models.UserModel;
import com.mytasks.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel saveUser(UserRecordDto userRecordDto) {

        UserModel user = new UserModel();

        user.setName(userRecordDto.name());
        user.setUsername(userRecordDto.username());
        user.setPassword(userRecordDto.password());

        return userRepository.save(user);
    }
}
