package com.design.patterns.states;

public class SecondYearKidState implements KidState
{

  @Override
  public void play()
  {
    System.out.println("Learn walking");

  }

  @Override
  public void eat()
  {
    System.out.println("Milk And Potatoes");

  }

}
