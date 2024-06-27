package com.tickitz.backend.auth.service.impl;

import com.tickitz.backend.auth.entity.UserAuth;
import com.tickitz.backend.users.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UsersRepository usersRepository;

  public UserDetailsServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var userData = usersRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    return new UserAuth(userData);
  }
}
