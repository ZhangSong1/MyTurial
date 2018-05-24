package com.cms.web.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.cms.web.service.WebSessionCookieService;
import com.cms.web.util.MemcachedUtils;
import com.cms.web.util.WebUtils;

public class SessionFilter extends HttpFilter
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Autowired
  WebSessionCookieService webSessionCookieService;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpSession session = request.getSession(true);
    Object obj = MemcachedUtils.get(session.getId());
    if(obj == null)
    {
      if(!webSessionCookieService.existSessionCookie(request))
      {
        HttpServletResponse response = (HttpServletResponse)res;
        response.sendRedirect(WebUtils.getHtml("login"));
      }
    }
    chain.doFilter(req, res);
  }

}
