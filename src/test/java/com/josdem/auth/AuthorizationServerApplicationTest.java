package com.josdem.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class AuthorizationServerApplicationTest {

  private final ApplicationContext applicationContext;

  @Test
  @DisplayName("Should load context")
  void shouldLoadContext(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    assertNotNull(applicationContext, "should start the application");
  }
}
