package com.design.patterns.decorator;

public class SkyTV implements SatelliteTV
{

  @Override
  public void show(int channelNumber)
  {
    if(channelNumber < 100)
    {
      System.out.println("Basic News Channels");
    }
    else
    {
      System.out.println("You are not subscribed-Please subscribe");
    }

  }

  @Override
  public int subscriptionCost()
  {
    return 100;

  }

}
