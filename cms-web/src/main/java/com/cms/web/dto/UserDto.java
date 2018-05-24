package com.cms.web.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int id;
  private String password;
  private String email;
  private List<RoleDto> roles;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
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


  public List<RoleDto> getRoles()
  {
    return roles;
  }

  public void setRoles(List<RoleDto> roles)
  {
    this.roles = roles;
  }


  public class Role
  {
    private String roleName;
    private List<Endpoint> endpoints;

    public String getRoleName()
    {
      return roleName;
    }

    public void setRoleName(String roleName)
    {
      this.roleName = roleName;
    }

    public List<Endpoint> getEndpoints()
    {
      return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints)
    {
      this.endpoints = endpoints;
    }

  }

  public class Endpoint
  {
    private String endpoint;
    private String name;

    public String getEndpoint()
    {
      return endpoint;
    }

    public void setEndpoint(String endpoint)
    {
      this.endpoint = endpoint;
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

  }

}
