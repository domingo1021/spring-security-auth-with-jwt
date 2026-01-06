package com.domingo1021.springsecurityjwt.services;

import com.domingo1021.springsecurityjwt.models.UserRepository;
import com.domingo1021.springsecurityjwt.services.dto.CustomizedSpringSecurityUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // AuthenticationManager needs this method to load user details during authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return CustomizedSpringSecurityUserDetails.fromUserEntity(user);
    }
}
