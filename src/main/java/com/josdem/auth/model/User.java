package com.josdem.auth.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String mobile;
  private Role role;
  private Boolean enabled = true;
  private Boolean accountNonExpired = true;
  private Boolean credentialsNonExpired = true;
  private Boolean accountNonLocked = true;
  private LocalDateTime dateCreated = LocalDateTime.now();
}
