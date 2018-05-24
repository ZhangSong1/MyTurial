package com.cms.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @description 一个自定义的filter，
 *              必须包含authenticationManager,accessDecisionManager,securityMetadataSource三个属性，
 *              我们的所有控制将在这三个类中实现
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter
{

  private static final Logger logger = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);
  private FilterInvocationSecurityMetadataSource fisMetadataSource;

  @Override
  public Class<?> getSecureObjectClass()
  {
    return FilterInvocation.class;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource()
  {
    return fisMetadataSource;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
  {
    logger.info("------------MyFilterSecurityInterceptor.doFilter()-----------开始拦截器了....");
    FilterInvocation fi = new FilterInvocation(request, response, chain);
    InterceptorStatusToken token = super.beforeInvocation(fi);

    try
    {
      fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      super.afterInvocation(token, null);
    }

    logger.info("------------MyFilterSecurityInterceptor.doFilter()-----------拦截器该方法结束了....");

  }

  @Override
  public void init(FilterConfig config) throws ServletException
  {

  }

  public void setFisMetadataSource(FilterInvocationSecurityMetadataSource fisMetadataSource)
  {
    this.fisMetadataSource = fisMetadataSource;
  }

  public FilterInvocationSecurityMetadataSource getFisMetadataSource()
  {
    return fisMetadataSource;
  }

}
