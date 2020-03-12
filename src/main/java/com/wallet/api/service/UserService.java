package com.wallet.api.service;

import com.wallet.api.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User u);
    Optional<User> findByEmail(String email);


}
