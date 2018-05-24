package com.cms.web.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.cms.web.rmi.AuthService;
import com.cms.web.service.UserService;
import com.cms.web.util.SpringContextUtil;

public class TokenServiceImpl implements PersistentTokenRepository
{

  @Autowired
  private UserService userService;

  @Override
  public void createNewToken(PersistentRememberMeToken token)
  {
    AuthService authService = (AuthService)SpringContextUtil.getBean("simpleAuthService");
    String tokenStr = authService.auth(token.getUsername(), userService.getPwdByUsername(token.getUsername()));
    token = new PersistentRememberMeToken(token.getUsername(), null, tokenStr, null);
  }

  @Override
  public void updateToken(String series, String tokenValue, Date lastUsed)
  {
    AuthService authService = (AuthService)SpringContextUtil.getBean("simpleAuthService");
    authService.auth(tokenValue);
  }

  @Override
  public PersistentRememberMeToken getTokenForSeries(String seriesId)
  {
    return null;
  }

  @Override
  public void removeUserTokens(String username)
  {
    AuthService authService = (AuthService)SpringContextUtil.getBean("simpleAuthService");

  }

}
