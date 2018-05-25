package com.design.patterns.decorator;

public class Exec
{

  public static void main(String[] args)
  {
    SatelliteTV satelliteTV = new Espn(new CartoonNetwork(new SkyTV()));
    satelliteTV.show(105);
    System.out.println("Prince:" + satelliteTV.subscriptionCost());
    
    SatelliteTV satelliteTV2 = new Espn(new Discovery(new CartoonNetwork(new SkyTV())));
    satelliteTV2.show(206);
    System.out.println("Prince:" + satelliteTV2.subscriptionCost());

  }
}
