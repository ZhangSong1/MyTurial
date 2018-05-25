package com.design.patterns.create.factory;

public class MobileFactory
{

  public static Mobile createMobile(String type)
  {
    if(type.equals(Mobile.SAMSUNG))
    {
      return new Samsung(100, "A9", "A9 GPU");
    }

    else if(type.equals(Mobile.SONY))
    {
      return new Sony(100, "A9");
    }
    else
    {
      return null;
    }
  }

}
