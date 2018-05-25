package com.design.patterns.decorator;

public class CartoonNetwork extends ChannelDecorator
{

  public CartoonNetwork(SatelliteTV satelliteTV)
  {
    super(satelliteTV);
  }

  @Override
  public void show(int channelNumber)
  {
    if(channelNumber > 300 && channelNumber < 400)
    {
      System.out.println("Enjoy Mickyyyyyy");
    }
    else
    {
      this.getSatelliteTV().show(channelNumber);
    }

  }

  @Override
  public int subscriptionCost()
  {
    return this.getSatelliteTV().subscriptionCost() + 6;
  }

}
