package org.cms.auth.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiServerStart
{
  @SuppressWarnings("resource")
  public static void main(String[] args)
  {
    new ClassPathXmlApplicationContext("org/cms/auth/config/applicationContext.xml");
    System.out.println("RMI服务端启动!");
  }
}
