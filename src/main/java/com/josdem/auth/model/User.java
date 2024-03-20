package com.josdem.auth.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  private String firstName;
  private String lastName;
  private String email;
  private String mobile;

  @Column(nullable = false)
  @Enumerated(STRING)
  private Role role;

  @Column(nullable = false)
  private Boolean enabled = true;

  @Column(nullable = false)
  private Boolean accountNonExpired = true;

  @Column(nullable = false)
  private Boolean credentialsNonExpired = true;

  @Column(nullable = false)
  private Boolean accountNonLocked = true;

  @Column(nullable = false)
  private LocalDateTime dateCreated = LocalDateTime.now();
}
