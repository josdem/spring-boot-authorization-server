package com.josdem.auth.service.impl;

import com.josdem.auth.exception.BusinessException;
import com.josdem.auth.model.User;
import com.josdem.auth.repository.UserRepository;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optional = userRepository.findByUsername(username);
    if (optional.isPresent()) {
      User user = optional.get();
      return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),
          Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
    } else {
      throw new BusinessException("User not found with username: " + username);
    }
  }
}
