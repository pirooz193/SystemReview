package com.example.systemreview.service.impl;

import com.example.systemreview.domain.User;
import com.example.systemreview.repository.UserRepository;
import com.example.systemreview.service.UserService;
import com.example.systemreview.service.dto.UserDTO;
import com.example.systemreview.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User savedUser = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(savedUser);
    }
}
