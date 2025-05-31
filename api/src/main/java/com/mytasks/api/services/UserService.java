package com.mytasks.api.services;

import com.mytasks.api.dtos.UserRecordDto;
import com.mytasks.api.models.UserModel;
import com.mytasks.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel saveUser(UserRecordDto userRecordDto) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRecordDto.password());

        UserModel user = new UserModel();

        user.setName(userRecordDto.name());
        user.setUsername(userRecordDto.username());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
