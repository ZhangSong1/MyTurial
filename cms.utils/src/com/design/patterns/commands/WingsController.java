package com.design.patterns.commands;

public class WingsController implements CommandController
{

  public final static String MOVE_UP = "move_up";
  public final static String MOVE_DOWN = "move_down";
  public final static String KEEP_FLAT = "keep_flat";
  public final static String TURN_RIGHT = "turn_right";
  public final static String TURN_LEFT = "turn_left";
  private Wings wings;

  public WingsController()
  {
    wings = new Wings();
  }

  @Override
  public void excute(String command)
  {
    switch(command)
    {
    case MOVE_UP:
      wings.moveUp();
      break;
    case MOVE_DOWN:
      wings.moveDown();
      break;
    case KEEP_FLAT:
      wings.keepFlat();
      break;
    case TURN_RIGHT:
      wings.turnRight();
      break;
    case TURN_LEFT:
      wings.turnLeft();
      break;
    default:
      System.out.println("Invalid command");
      break;
    }

  }

  @Override
  public boolean canHandle(String command)
  {
    if(MOVE_UP.equals(command) || MOVE_DOWN.equals(command) || KEEP_FLAT.equals(command) || TURN_RIGHT.equals(command)
        || TURN_LEFT.equals(command))
    {
      return true;
    }
    return false;

  }

}
