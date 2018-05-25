package com.design.patterns.singleton;

public class Satellite
{
  private Satellite()
  {

  }

  private static class SatelliteHelper
  {
    public final static Satellite _innerInstance = new Satellite();
  }

  public static Satellite getSatellite()
  {
    return SatelliteHelper._innerInstance;
  }

  public static void main(String[] args)
  {
    for(int i = 0; i < 5; i++)
    {
      System.out.println(Satellite.getSatellite().hashCode());
    }
  }
}
