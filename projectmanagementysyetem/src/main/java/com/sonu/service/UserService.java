package com.sonu.service;

import com.sonu.model.User;

public interface UserService {
    User findUserProfileByToken(String token) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user, int number);

}
