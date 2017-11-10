package com.design.patterns.decorator;

public class Discovery extends ChannelDecorator
{

  public Discovery(SatelliteTV satelliteTV)
  {
    super(satelliteTV);
  }

  @Override
  public void show(int channelNumber)
  {
    if(channelNumber > 200 && channelNumber < 300)
    {
      System.out.println("Enjoy the Sleepy Documentary");
    }
    else
    {
      this.getSatelliteTV().show(channelNumber);
    }

  }

  @Override
  public int subscriptionCost()
  {
    return this.getSatelliteTV().subscriptionCost() + 10;
  }

}
