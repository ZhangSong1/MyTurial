package com.design.patterns.decorator;

public class Espn extends ChannelDecorator
{

  public Espn(SatelliteTV satelliteTV)
  {
    super(satelliteTV);
  }

  @Override
  public void show(int channelNumber)
  {
    if(channelNumber > 100 && channelNumber < 200)
    {
      System.out.println("Enjoy football match");
    }
    else
    {
      this.getSatelliteTV().show(channelNumber);
    }

  }

  @Override
  public int subscriptionCost()
  {
    return this.getSatelliteTV().subscriptionCost() + 7;
  }

}
