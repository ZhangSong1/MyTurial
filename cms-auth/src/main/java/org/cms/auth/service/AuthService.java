package org.cms.auth.service;

import org.cms.auth.entity.Token;

public interface AuthService
{
  public String auth(String username,String password);
  
  public String auth(String tokenStr);

  public Token refreshToken(Token oldToken);

  public void logout(String tokenStr);
}
