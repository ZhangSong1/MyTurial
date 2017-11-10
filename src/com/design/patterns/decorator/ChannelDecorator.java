package com.design.patterns.decorator;


/*
 * 1. Adding behaviour statically or dynamically
 * 2. Extending functionality without effecting the behaviour of the other objects
 * 3. Adhering to Open for extension,closed for modification
 */
public abstract class ChannelDecorator implements SatelliteTV
{
  private SatelliteTV satelliteTV;

  public ChannelDecorator(SatelliteTV satelliteTV)
  {
    this.satelliteTV = satelliteTV;
  }
  //Adding behaviour
  public SatelliteTV getSatelliteTV()
  {
    return this.satelliteTV;
  }
 
}
