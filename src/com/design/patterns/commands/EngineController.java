package com.design.patterns.commands;

public class EngineController implements CommandController
{
  public final static String SPEED_UP = "speed_up";
  public final static String SLOW_DOWN = "slow_down";
  private Engine engine;

  public EngineController()
  {
    this.engine = new Engine();
  }

  @Override
  public void excute(String command)
  {
    switch(command)
    {
    case SPEED_UP:
      engine.speedUp();
      break;
    case SLOW_DOWN:
      engine.slowDown();
      break;
    default:
      System.out.println("Invalid command");
      break;
    }
  }

  @Override
  public boolean canHandle(String command)
  {
    if(SPEED_UP.equals(command) || SLOW_DOWN.equals(command))
    {
      return true;
    }
    return false;
  }

}
