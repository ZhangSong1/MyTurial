package com.cms.web.facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.web.model.User;

public interface UserFacade
{
  public boolean validatePwd(String username,String password);
  public void create(User user,HttpServletRequest request, HttpServletResponse resopnse);
}
