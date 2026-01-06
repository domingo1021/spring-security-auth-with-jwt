package com.domingo1021.springsecurityjwt.models;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRepository implements UserRepository {

    // Using a thread-safe Set to store users in memory
    Set<User> userDb = ConcurrentHashMap.newKeySet();

    public InMemoryRepository() {
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDb.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
    }

    @Override
    public void save(User user) {
        user.setId(userDb.size() + 1L);
        userDb.add(user);
    }
}
