package com.cms.web.facade;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.web.model.User;
import com.cms.web.service.UserService;
import com.cms.web.service.WebSessionCookieService;
import com.cms.web.util.MemcachedUtils;

@Component
public class UserFacadeImpl implements UserFacade
{

  private UserService userService;
  private WebSessionCookieService webSessionCookieService;

  @Autowired
  public UserFacadeImpl(UserService userService,WebSessionCookieService webSessionCookieService)
  {
    this.userService = userService;
    this.webSessionCookieService = webSessionCookieService;
  }

  @Override
  public boolean validatePwd(String username, String password)
  {

    return DigestUtils.md5Hex(password).equals(userService.getPwdByUsername(username));
  }

  @Override
  public void create(User user,HttpServletRequest request, HttpServletResponse resopnse)
  {
    userService.save(user);
    String sessionId = request.getSession(true).getId();
    MemcachedUtils.add(sessionId, user, new Date(1000 * 3600 * 3));
    webSessionCookieService.addSessionCookie(sessionId, resopnse);
  }

}
