package com.josdem.auth.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.josdem.auth.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

  private final ApplicationConfig applicationConfig;

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
                    .roles(Roles.USER.name())
                    .build();
    return new InMemoryUserDetailsManager(user);
  }
}