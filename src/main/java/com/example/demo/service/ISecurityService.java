package com.example.demo.service;

public interface ISecurityService {

    String findLoggedInUserName();
    void autoLogin(String username, String password);
}
