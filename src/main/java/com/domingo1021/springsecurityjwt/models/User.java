package com.domingo1021.springsecurityjwt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class User {

    public static final String USER = "USER";

    @Setter
    private Long id;

    private String username;

    private String hashedPassword;

    private String role = USER;

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public User(String username, String hashedPassword, String role) {
        this(username, hashedPassword);
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

        var user = (User) o;
        return this.username.equals(user.username);
    }
}
