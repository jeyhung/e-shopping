package com.jeyhung.identity.service;

import com.jeyhung.identity.model.User;

public interface UserService {
    User confirm(long id, User obj);
}
