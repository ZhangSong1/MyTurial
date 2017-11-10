package com.design.patterns.states;

public class FirstYearKidState implements KidState
{

  @Override
  public void play()
  {
    System.out.println("Play cards");

  }

  @Override
  public void eat()
  {
    System.out.println("Milk");

  }

}
