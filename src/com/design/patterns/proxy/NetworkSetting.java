package com.design.patterns.proxy;

public class NetworkSetting
{

  private ISP proxy;
  private ISP provider;

  public NetworkSetting()
  {
    this.provider = new Vodafone();
    this.proxy = new ISPProxy(this);
  }

  public ISP getInternet()
  {
    return proxy;
  }

  public ISP getISP()
  {
    return provider;
  }

}
