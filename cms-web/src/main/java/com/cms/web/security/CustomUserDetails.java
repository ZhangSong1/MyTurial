package com.cms.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cms.web.model.Role;
import com.cms.web.model.User;

public class CustomUserDetails implements UserDetails
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private User user;

  public CustomUserDetails(User user)
  {
    this.user = (User)user.clone();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {

    Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
    Set<Role> roles = user.getRoles();
    roles.stream().forEach(role -> {
      role.getMenus().stream().forEach(menu -> {
        authSet.add(new GrantedAuthorityImpl(menu.getMenuName()));
      });
    });
    return authSet;
  }

  @Override
  public String getPassword()
  {
    return user.getPassword();
  }

  @Override
  public String getUsername()
  {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }

}
