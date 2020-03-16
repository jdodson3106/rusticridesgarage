package com.justindodson.rusticridesgarage.auth.service;


import com.justindodson.rusticridesgarage.auth.model.entity.User;

public interface UserService {
    void createNewUser(User user);
    void updateUser(User user);
    void getUserByUsername(String username);
    User deleteUser(long id);
    User getUserById(long id);
}
