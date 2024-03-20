package com.josdem.auth.repository;

import com.josdem.auth.model.User;
import java.util.Optional;

public interface UserRepository {
  Optional<User> findByUsername(String username);
}
