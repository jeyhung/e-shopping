package com.jeyhung.identity.repository;

import com.jeyhung.identity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
