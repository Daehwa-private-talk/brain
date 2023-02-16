package com.daehwa.brain.app.service;

import com.daehwa.brain.app.jpa.UserEntity;
import com.daehwa.brain.app.jpa.UserRepository;
import com.daehwa.brain.app.mapper.UserMapper;
import com.daehwa.brain.app.model.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword(encoder.encode(user.getPassword()));

        UserEntity userEntity = userMapper.dtoToEntity(user);

        userRepository.save(userEntity);

        return user;
    }
}
