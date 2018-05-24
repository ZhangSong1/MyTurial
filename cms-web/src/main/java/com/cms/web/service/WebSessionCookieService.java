package com.cms.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebSessionCookieService
{
  public void addSessionCookie(String sessionId,HttpServletResponse resopnse);

  public void deleteSessionCookie(HttpServletRequest request);

  public void refreshSessionCookie(HttpServletRequest request,HttpServletResponse resopnse);
  
  public boolean existSessionCookie(HttpServletRequest request);
}
