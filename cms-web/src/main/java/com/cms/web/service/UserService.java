package com.cms.web.service;

import java.util.List;

import com.cms.web.model.Role;
import com.cms.web.model.User;

public interface UserService
{
  public String getPwdByUsername(String name);
  public void save(User user);
  public User getUserByUsername(String username);
  public List<Role> getAllRoles();
  
  
}
