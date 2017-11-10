package com.design.patterns.observer;

public class Mobile implements Observer
{

  @Override
  public void updateStock(Stock stock)
  {
    System.out.println("Mobile Publish Stock:" + stock.getName() + " Price:" + stock.getPrice());

  }

}
