package com.josdem.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("app")
public class ApplicationConfig {
  private String serverUrl;
  private String loginClientUrl;
  private String authorizedUrl;
  private String clientUrl;
  private String credentialsClientId;
  private String credentialsClientSecret;
  private String authClientId;
  private String authClientSecret;
  private String username;
  private String password;
}
