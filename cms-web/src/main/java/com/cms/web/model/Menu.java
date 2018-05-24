package com.cms.web.model;

import java.io.Serializable;

public class Menu implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int menuId;
  private String menuName;
  private String endpoint;

  public Menu()
  {

  }

  public Menu(int menuId, String menuName, String endpoint)
  {
    this.menuId = menuId;
    this.menuName = menuName;
    this.endpoint = endpoint;
  }

  public int getMenuId()
  {
    return menuId;
  }

  public void setMenuId(int menuId)
  {
    this.menuId = menuId;
  }

  public String getMenuName()
  {
    return menuName;
  }

  public void setMenuName(String menuName)
  {
    this.menuName = menuName;
  }

  public String getEndpoint()
  {
    return endpoint;
  }

  public void setEndpoint(String endpoint)
  {
    this.endpoint = endpoint;
  }

}
