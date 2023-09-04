package com.josdem.auth;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.josdem.auth.util.CredentialsEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CredentialsClientTest {

  public static final String WRITE = "write";
  public static final String BASIC = "Basic";

  private final MockMvc mockMvc;

  @Test
  @DisplayName("it gets client credentials")
  void shouldGetClientCredentials(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc
        .perform(
            post("/oauth2/token")
                .header(
                    AUTHORIZATION,
                    String.format(BASIC + " %s", CredentialsEncoder.encode("client", "secret")))
                .param("grant_type", CLIENT_CREDENTIALS.getValue())
                .param("scope", WRITE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.access_token").isNotEmpty())
        .andExpect(jsonPath("$.token_type").value("Bearer"))
        .andExpect(jsonPath("$.expires_in").isNumber())
        .andExpect(jsonPath("$.scope").value(WRITE));
  }
}
