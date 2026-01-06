package com.domingo1021.springsecurityjwt.models;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    void save(User user);
}
