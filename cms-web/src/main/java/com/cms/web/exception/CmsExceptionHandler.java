package com.cms.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CmsExceptionHandler implements HandlerExceptionResolver 
{

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
  {
    ModelAndView model=new ModelAndView("error");
    if(ex instanceof CmsServiceException){
      model.addObject("message", ex.getMessage());
    }
    return null;
  }

}
