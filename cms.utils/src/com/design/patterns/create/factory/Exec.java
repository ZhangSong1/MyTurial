package com.design.patterns.create.factory;

public class Exec
{

  public static void main(String[] args)
  {
    Mobile mobile = MobileFactory.createMobile(Mobile.SAMSUNG);
    mobile.printMobileInfo();
    Mobile mobile1 = MobileFactory.createMobile(Mobile.SONY);
    mobile1.printMobileInfo();
  }

}
