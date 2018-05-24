package com.cms.web.service;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class WebSessionCookieServiceImpl implements WebSessionCookieService
{

  @Override
  public void addSessionCookie(String sessionId, HttpServletResponse resopnse)
  {
    Cookie cookie = new Cookie("cmsSessionId", sessionId);
    cookie.setMaxAge(1000 * 3600 * 3);
    cookie.setHttpOnly(true);
    cookie.setDomain("/cms-web");
    cookie.setPath("/");
    resopnse.addCookie(cookie);
  }

  @Override
  public void deleteSessionCookie(HttpServletRequest request)
  {
    Consumer<Cookie> action = new Consumer<Cookie>()
    {

      @Override
      public void accept(Cookie cookie)
      {
        if(cookie.getName().equals("cmsSessionId"))
        {
          cookie.setMaxAge(0);
        }

      }
    };
    Arrays.stream(request.getCookies()).forEach(action);

  }

  @Override
  public void refreshSessionCookie(HttpServletRequest request, HttpServletResponse resopnse)
  {
    Consumer<Cookie> action = new Consumer<Cookie>()
    {

      @Override
      public void accept(Cookie cookie)
      {
        if(cookie.getName().equals("cmsSessionId"))
        {
          cookie.setMaxAge(1000 * 3600 * 3);
          resopnse.addCookie(cookie);
        }

      }
    };
    Arrays.stream(request.getCookies()).forEach(action);

  }

  @Override
  public boolean existSessionCookie(HttpServletRequest request)
  {
    return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("cmsSessionId")).count() > 0;

  }

}
