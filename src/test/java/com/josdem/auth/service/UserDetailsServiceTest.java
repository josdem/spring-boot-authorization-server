package com.josdem.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.josdem.auth.model.Role;
import com.josdem.auth.model.User;
import com.josdem.auth.repository.UserRepository;
import com.josdem.auth.service.impl.UserDetailsServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
class UserDetailsServiceTest {

  private static final String USERNAME = "josdem";

  private UserDetailsServiceImpl userDetailsService;

  @Mock private UserRepository userRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    userDetailsService = new UserDetailsServiceImpl(userRepository);
  }

  @Test
  @DisplayName("loading user by username")
  void shouldLoadUserByUsername(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    User user = new User();
    user.setUsername(USERNAME);
    user.setPassword("password");
    user.setEnabled(true);
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCredentialsNonExpired(true);
    user.setRole(Role.USER);
    when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));

    UserDetails result = userDetailsService.loadUserByUsername(USERNAME);

    assertEquals(user.getUsername(), result.getUsername());
    assertEquals(user.getPassword(), result.getPassword());
    assertEquals(user.getEnabled(), result.isEnabled());
  }
}
