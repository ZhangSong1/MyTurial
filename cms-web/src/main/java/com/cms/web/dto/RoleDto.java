package com.cms.web.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int id;
  private String roleName;
  private List<EndpointDto> endpoints;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getRoleName()
  {
    return roleName;
  }

  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }

  public List<EndpointDto> getEndpoints()
  {
    return endpoints;
  }

  public void setEndpoints(List<EndpointDto> endpoints)
  {
    this.endpoints = endpoints;
  }

}
