package com.design.patterns.create.factory;

public class Samsung implements Mobile
{
  private int ramSize;
  private String processor;
  private String processorGPU;

  public Samsung(int ramSize, String processor, String processorGPU)
  {
    this.ramSize = ramSize;
    this.processor = processor;
    this.processorGPU = processorGPU;
  }

  @Override
  public void printMobileInfo()
  {
    System.out.println("Sony ramSize:" + this.ramSize + " processor:" + this.processor+" GPU:"+this.processorGPU);

  }

}
