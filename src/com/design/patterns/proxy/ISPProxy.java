package com.design.patterns.proxy;

import java.util.Calendar;
/**
 * What is Proxy?
 * Something that is authorized to handle the requests on behalf of the original objects
 * Why do we need it?
 * Do something more which is not in scope of the original objects
 * To handle certain functionality
 * Common application
 * Pre processing
 * Post processing
 * Overring functionality
 * Caching mechanisms
 * Security mechanisms
 */
public class ISPProxy implements ISP
{
  /**
   * Proxy is used to log and filter request
   */
  private NetworkSetting networkSetting;

  public ISPProxy(NetworkSetting networkSetting)
  {
    this.networkSetting = networkSetting;
  }

  @Override
  public String getISPResource(String site)
  {
    logRequest(site);
    if(isBlocked(site))
    {
      return "The site is blocked as per company policy";
    }
    return networkSetting.getISP().getISPResource(site);
  }

  private void logRequest(String site)
  {
    System.out.println(Calendar.getInstance().getTime() + " Request for " + site);
  }

  private boolean isBlocked(String site)
  {
    switch(site)
    {
    case "www.google.com":
      return false;
    case "www.yahoo.com":
      return false;
    default:
      return true;
    }
  }

}
