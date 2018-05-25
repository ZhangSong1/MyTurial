package com.design.patterns.proxy;

public class Vodafone implements ISP
{

  @Override
  public String getISPResource(String site)
  {
    switch(site)
    {
    case "www.google.com":
      return "Gooooooooooooooooogle";
    case "www.yahoo.com":
      return "Yahoooooooooooooooooo";
    default:
      return "Sorry for ";
    }
  }

}
