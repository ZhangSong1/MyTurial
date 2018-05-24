package com.cms.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

public class RememberMeServicesImpl extends AbstractRememberMeServices
{

  private PersistentTokenRepository tokenRepository = null;
  
  protected RememberMeServicesImpl(String key, UserDetailsService userDetailsService,PersistentTokenRepository tokenRepository)
  {
    super(key, userDetailsService);
    this.tokenRepository = tokenRepository;
  }

  @Override
  protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication)
  {
    String username = successfulAuthentication.getName();
    logger.debug("Creating new persistent login for user " + username);

    PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(
        username, null, null, null);
    try {
      tokenRepository.createNewToken(persistentToken);
      addCookie(persistentToken, request, response);
    }
    catch (Exception e) {
      logger.error("Failed to save persistent token ", e);
    }
    
  }
  
  private void addCookie(PersistentRememberMeToken token, HttpServletRequest request,
      HttpServletResponse response) {
    setCookie(new String[] {token.getTokenValue()},
        getTokenValiditySeconds(), request, response);
  }

  @Override
  protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response)
      throws RememberMeAuthenticationException, UsernameNotFoundException
  {
    // TODO Auto-generated method stub
    return null;
  }

}
