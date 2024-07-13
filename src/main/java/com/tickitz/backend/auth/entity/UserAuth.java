package com.tickitz.backend.auth.entity;

import com.tickitz.backend.users.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
public class UserAuth extends Users implements UserDetails {
  private final Users user;

  public UserAuth(Users user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
//    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//    authorities.add(() -> "ROLE_USER");
//    authorities.add(() -> user.getRole().name());
//    return authorities;
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }
}
