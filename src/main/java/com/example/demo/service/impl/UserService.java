package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDTO user) {
        UserEntity userEntity = userConverter.toEntity(user);
        userEntity.setRoles(new ArrayList<>(roleRepository.findByName("USER")));
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity != null) {
            return userConverter.toDTO(userEntity);
        }
        return null;
    }

    @Override
    public UserDTO findByUserName(String username) {
        UserEntity userEntity = userRepository.findByUserName(username);
        if(userEntity != null) {
            return userConverter.toDTO(userEntity);
        }
        return null;
    }
}
