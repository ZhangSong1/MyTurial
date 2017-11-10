package com.design.patterns.observer;

public class Exec
{

  public static void main(String[] args)
  {

    Apple apple = new Apple();
    IBM iBM = new IBM();
    Microsoft microsoft = new Microsoft();

    Mobile mobile = new Mobile();
    Desktop desktop = new Desktop();

    apple.addObserver(mobile);
    apple.addObserver(desktop);
    iBM.addObserver(desktop);
    iBM.addObserver(mobile);
    microsoft.addObserver(desktop);
    microsoft.addObserver(mobile);

    for(int i = 0; i < 10; i++)
    {
      apple.updatePrice((int)(Math.random() * 100));
      iBM.updatePrice((int)(Math.random() * 100));
      microsoft.updatePrice((int)(Math.random() * 100));
    }

  }

}
