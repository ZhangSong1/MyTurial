package com.design.patterns.create.factory;

public class Sony implements Mobile
{

  private int ramSize;
  private String processor;

  public Sony(int ramSize, String processor)
  {
    this.ramSize = ramSize;
    this.processor = processor;
  }

  @Override
  public void printMobileInfo()
  {
    System.out.println("Sony ramSize:" + this.ramSize + " processor:" + this.processor);

  }

}
