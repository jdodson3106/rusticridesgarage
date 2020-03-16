package com.justindodson.rusticridesgarage.bootstrap;

import com.justindodson.rusticridesgarage.auth.model.entity.User;
import com.justindodson.rusticridesgarage.auth.model.repository.UserRepository;
import com.justindodson.rusticridesgarage.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public BootstrapData(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Justin", "Dodson", "admin", "test@mail.com", "asdf", "asdf");
        userService.createNewUser(user);
    }
}
