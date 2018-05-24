package com.cms.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cms.web.model.User;
import com.cms.web.service.UserService;

public class CustomUserDetailsService implements UserDetailsService
{

  private UserService userService;
  
  public CustomUserDetailsService(UserService userService)
  {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    User user = userService.getUserByUsername(username);
    if(user == null)
    {
      throw new UsernameNotFoundException(username);
    }
    return new CustomUserDetails(user);
  }
  
  

}
