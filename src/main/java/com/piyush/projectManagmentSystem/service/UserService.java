package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.entity.User;

import java.util.Optional;

public interface UserService {
    User findUserProfileByJwt(String jwt ) throws Exception;

    User findUserByEmail(String email) throws Exception;

    Optional<User> findUserById(Long id ) throws Exception;
    User updateUsersProjectSize(User user , int number) throws Exception;
}
