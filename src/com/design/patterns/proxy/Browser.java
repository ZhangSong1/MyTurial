package com.design.patterns.proxy;

import javax.swing.JOptionPane;

public class Browser
{
  NetworkSetting networkSetting = null;

  public Browser()
  {
    this.networkSetting = new NetworkSetting();
  }

  public void sendRequest()
  {
    String site = JOptionPane.showInputDialog("Enter the site URL:");
    String response = networkSetting.getInternet().getISPResource(site);
    handleResponse(response);

  }

  public void handleResponse(String response)
  {
    System.out.println(response);
  }
}
