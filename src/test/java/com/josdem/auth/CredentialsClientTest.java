package com.josdem.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CredentialsClientTest {

  private final WebTestClient webTestClient;

  @Test
  @DisplayName("it gets client credentials")
  void shouldGetClientCredentials(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .post()
        .uri("/oauth2/token")
        .bodyValue("grant_type=client_credentials")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.access_token")
        .isNotEmpty()
        .jsonPath("$.token_type")
        .isEqualTo("bearer")
        .jsonPath("$.scope")
        .isEqualTo("read write")
        .jsonPath("$.expires_in")
        .isNotEmpty()
        .jsonPath("$.jti")
        .isNotEmpty();
  }
}
