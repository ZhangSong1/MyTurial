package com.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cms.web.facade.UserFacade;
import com.cms.web.model.User;
import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;

@Controller
@RequestMapping(WebConstants.SIGN_UP_PATH)
public class SignUpController
{
  private UserFacade userFacade;

  @Autowired
  public SignUpController(UserFacade userFacade)
  {
    this.userFacade = userFacade;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView signUp(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse resopnse)
  {
    Assert.notNull(user.getEmail(), "电子邮箱不能为空");
    Assert.notNull(user.getPassword(), "密码不能为空");
    userFacade.create(user, request, resopnse);
    return new ModelAndView(WebUtils.getHtml("main"));
  }
}
