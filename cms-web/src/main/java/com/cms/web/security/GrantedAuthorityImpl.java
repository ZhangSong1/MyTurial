package com.cms.web.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String menuName;

  public GrantedAuthorityImpl(String menuName)
  {
    this.menuName = menuName;
  }

  @Override
  public String getAuthority()
  {
    return menuName;
  }

}
