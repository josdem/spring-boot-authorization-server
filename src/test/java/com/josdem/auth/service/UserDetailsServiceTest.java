package com.josdem.auth.service;

import com.josdem.auth.repository.UserRepository;
import com.josdem.auth.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;

@Slf4j
class UserDetailsServiceTest {

  private static final String USERNAME = "josdem";

  private UserDetailsService userDetailsService;

  @Mock private UserRepository userRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    userDetailsService = new UserDetailsServiceImpl(userRepository);
  }
}
