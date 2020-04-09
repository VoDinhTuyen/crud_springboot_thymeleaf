package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

public interface IUserService {

    void save(UserDTO user);
    UserDTO findByEmail(String email);
    UserDTO findByUserName(String username);
}
