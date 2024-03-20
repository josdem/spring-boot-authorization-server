package com.josdem.auth.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.josdem.auth.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

  private final ApplicationConfig applicationConfig;
  private final UserDetailsService userDetailsService;

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        .formLogin(withDefaults());
    return http.build();
  }

  @Bean
  UserDetailsService users() {
    UserDetails user =
        User.withDefaultPasswordEncoder()
            .username(applicationConfig.getUsername())
            .password(applicationConfig.getPassword())
            .roles(Role.USER.name())
            .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return authenticationProvider;
  }
}
