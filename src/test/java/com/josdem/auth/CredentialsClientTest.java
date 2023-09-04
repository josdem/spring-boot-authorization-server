package com.josdem.auth;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.josdem.auth.config.ApplicationConfig;
import com.josdem.auth.util.CredentialsEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ActiveProfiles("test")
class CredentialsClientTest {

  public static final String WRITE = "write";
  public static final String BASIC = "Basic";
  public static final String TEST_PASSWORD = "secret";
  public static final String BEARER = "Bearer";

  private final MockMvc mockMvc;

  private final ApplicationConfig applicationConfig;

  @Test
  @DisplayName("it gets client credentials")
  void shouldGetClientCredentials(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc
        .perform(
            post("/oauth2/token")
                .header(
                    AUTHORIZATION,
                    String.format(
                        BASIC + " %s",
                        CredentialsEncoder.encode(applicationConfig.getClientId(), TEST_PASSWORD)))
                .param("grant_type", CLIENT_CREDENTIALS.getValue())
                .param("scope", WRITE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.access_token").isNotEmpty())
        .andExpect(jsonPath("$.token_type").value(BEARER))
        .andExpect(jsonPath("$.expires_in").isNumber())
        .andExpect(jsonPath("$.scope").value(WRITE));
  }

  @Test
  @DisplayName("it gets error due to invalid client credentials")
  void shouldNotGetClientCredentials(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc
        .perform(
            post("/oauth2/token")
                .header(
                    AUTHORIZATION,
                    String.format(
                        BASIC + " %s",
                        CredentialsEncoder.encode("not-valid-user", "not-valid-password")))
                .param("grant_type", CLIENT_CREDENTIALS.getValue())
                .param("scope", WRITE))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @DisplayName("it gets unsupported grant type")
  void shouldGetUnsupportedGrantType(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc
        .perform(
            post("/oauth2/token")
                .header(
                    AUTHORIZATION,
                    String.format(
                        BASIC + " %s",
                        CredentialsEncoder.encode(applicationConfig.getClientId(), TEST_PASSWORD)))
                .param("grant_type", "not-valid-grant-type")
                .param("scope", WRITE))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("it gets unsupported scope")
  void shouldGetUnsupportedScope(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc
        .perform(
            post("/oauth2/token")
                .header(
                    AUTHORIZATION,
                    String.format(
                        BASIC + " %s",
                        CredentialsEncoder.encode(applicationConfig.getClientId(), TEST_PASSWORD)))
                .param("grant_type", CLIENT_CREDENTIALS.getValue())
                .param("scope", "not-valid-scope"))
        .andExpect(status().isBadRequest());
  }
}
