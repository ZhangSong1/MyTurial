package com.cms.web.dto;

import java.io.Serializable;

public class EndpointDto implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int id;
  private String endpoint;
  private String name;
  public int getId()
  {
    return id;
  }
  public void setId(int id)
  {
    this.id = id;
  }
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
