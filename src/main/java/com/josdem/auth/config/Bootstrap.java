package com.josdem.auth.config;

import com.josdem.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

  private final Environment environment;
  private final UserRepository userRepository;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("Loading development environment");
    createDefaultUsers();
  }

  private void createDefaultUsers() {
    log.info("Creating default users");
  }
}
