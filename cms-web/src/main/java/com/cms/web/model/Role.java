package com.cms.web.model;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int roleId;
  private String roleName;
  private Set<Menu> menus;

  public Role()
  {

  }

  public Role(int roleId, String roleName, Set<Menu> menus)
  {
    this.roleId = roleId;
    this.roleName = roleName;
    this.menus = menus;
  }

  public int getRoleId()
  {
    return roleId;
  }

  public void setRoleId(int roleId)
  {
    this.roleId = roleId;
  }

  public String getRoleName()
  {
    return roleName;
  }

  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }

  public Set<Menu> getMenus()
  {
    return menus;
  }

  public void setMenus(Set<Menu> menus)
  {
    this.menus = menus;
  }

}
