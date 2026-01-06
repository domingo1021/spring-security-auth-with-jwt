package com.domingo1021.springsecurityjwt.services.dto;

import com.domingo1021.springsecurityjwt.models.User;
import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *  Implementation of UserDetails that will be encapsulated into Authentication objects
 */
@AllArgsConstructor
public class CustomizedSpringSecurityUserDetails implements UserDetails {

    private String username;
    private String password;

    @Getter
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static CustomizedSpringSecurityUserDetails fromUserEntity(User user) {
        return new CustomizedSpringSecurityUserDetails(
            user.getUsername(),
            user.getHashedPassword(),
            user.getRole()
        );
    }
}
