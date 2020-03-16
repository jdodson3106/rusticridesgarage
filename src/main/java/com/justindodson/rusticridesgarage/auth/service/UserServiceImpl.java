package com.justindodson.rusticridesgarage.auth.service;

import com.justindodson.rusticridesgarage.auth.model.entity.User;
import com.justindodson.rusticridesgarage.auth.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void createNewUser(User user) {
        user.setPassword1(encoder.encode(user.getPassword1()));
        user.setPassword2(encoder.encode(user.getPassword2()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void getUserByUsername(String username) {

    }

    @Override
    public User deleteUser(long id) {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }
}
