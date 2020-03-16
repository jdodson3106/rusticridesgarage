package com.justindodson.rusticridesgarage.auth.service;

import com.justindodson.rusticridesgarage.auth.model.entity.User;
import com.justindodson.rusticridesgarage.auth.UserPrincipal;
import com.justindodson.rusticridesgarage.auth.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RRGUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public RRGUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if(null == user) {
            throw new UsernameNotFoundException("Cannot find user with username: " + username);
        }

        return new UserPrincipal(user);
    }
}
