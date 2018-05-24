package com.cms.web.rmi;

public interface AuthService
{
  public String auth(String username,String password);
  
  public String auth(String tokenStr);

  public Token refreshToken(Token oldToken);

  public void logout(String tokenStr);
}
