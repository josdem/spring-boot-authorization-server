package com.josdem.auth.config;

import com.josdem.auth.model.Role;
import com.josdem.auth.model.User;
import com.josdem.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

  private final UserRepository userRepository;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("Validating default user");
    createUserWithRole("josdem", "12345678", "contact@josdem.io", Role.USER);
  }

  void createUserWithRole(String username, String password, String email, Role authority) {
    if (userRepository.findByUsername(username).isEmpty()) {
      log.info("Creating user: {}", username);
      User user = new User();
      user.setUsername(username);
      user.setPassword(new BCryptPasswordEncoder().encode(password));
      user.setEmail(email);
      user.setRole(authority);
      user.setFirstName(username);
      user.setLastName(username);
      user.setEnabled(true);
      userRepository.save(user);
    }
  }
}
