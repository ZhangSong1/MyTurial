package com.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;

import org.springframework.security.core.AuthenticationException;

@Controller
public class LoginController
{

  @RequestMapping(value = WebConstants.LOGIN_PATH, method = RequestMethod.GET)
  public ModelAndView showLogin(ModelMap map,HttpServletRequest request, HttpServletResponse resopnse)
  {
    AuthenticationException exception = (AuthenticationException)(request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
    if(exception != null){
      map.addAttribute("error", exception.getLocalizedMessage());
    }
    return new ModelAndView(WebUtils.getJsp("login"), HttpStatus.NON_AUTHORITATIVE_INFORMATION);

  }

  @RequestMapping(value = WebConstants.LOGIN_FAILED_PATH, method = RequestMethod.GET)
  public String loginFailed(ModelMap map)
  {
    return "redirect:login";

  }

  @RequestMapping(value = WebConstants.MAIN_PATH, method = RequestMethod.GET)
  public ModelAndView showMain(HttpServletRequest request, HttpServletResponse resopnse)
  {

    return new ModelAndView(WebUtils.getHtml("main"));

  }

  @RequestMapping(value = WebConstants.LOGOUT_PATH, method = RequestMethod.GET)
  public String logout(HttpServletRequest request, HttpServletResponse resopnse)
  {
    
    return "forward:/login";

  }

}
