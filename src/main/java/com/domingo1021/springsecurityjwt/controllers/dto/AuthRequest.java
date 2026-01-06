package com.domingo1021.springsecurityjwt.controllers.dto;

import com.domingo1021.springsecurityjwt.services.dto.UsernamePasswordPair;

public record AuthRequest(String username, String password) {

    public UsernamePasswordPair toUsernamePasswordPair() {
        return new UsernamePasswordPair(username, password);
    }
}
