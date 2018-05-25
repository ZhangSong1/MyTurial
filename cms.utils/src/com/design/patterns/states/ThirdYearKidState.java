package com.design.patterns.states;

public class ThirdYearKidState implements KidState
{

  @Override
  public void play()
  {
    System.out.println("Play toy");

  }

  @Override
  public void eat()
  {
    System.out.println("Rice");

  }

}
