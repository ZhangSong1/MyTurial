package com.cms.web.model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private int id;
  private String password;
  private String email;

  private Set<Role> roles;

  public User()
  {

  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Set<Role> getRoles()
  {
    return roles;
  }

  public void setRoles(Set<Role> roles)
  {
    this.roles = roles;
  }

  @Override
  public Object clone()
  {
    User o = null;
    try
    {
      o = (User)super.clone();
    }
    catch(CloneNotSupportedException e)
    {
      e.printStackTrace();
    }
    return o;
  }

}
