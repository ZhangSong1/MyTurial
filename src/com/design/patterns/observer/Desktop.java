package com.design.patterns.observer;

public class Desktop implements Observer
{
  @Override
  public void updateStock(Stock stock)
  {
    System.out.println("Desktop Publish Stock " + stock.getName() + " Price " + stock.getPrice());

  }
}
