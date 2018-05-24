package com.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;

@Controller
@RequestMapping(WebConstants.FORGOT_PASSWORD_PATH)
public class ForgotPwdController
{
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView forgotPwdPage(HttpServletRequest request, HttpServletResponse resopnse)
  {
    return new ModelAndView(WebUtils.getHtml("forgotPwd"));
  }
}
