package com.design.patterns.commands;

import java.util.ArrayList;
import java.util.List;

public class Pilot
{

  private Cockpit cockpit;

  public Pilot(Cockpit cockpit)
  {
    this.cockpit = cockpit;
  }

  public void fly()
  {
    System.out.println("Begin flying");
    cockpit.excute(Cockpit.TAKE_OFF);
  }

  public void land()
  {
    System.out.println("Begin landing");
    cockpit.excute(Cockpit.LAND);
  }

  public static void main(String[] args)
  {
    List<CommandController> commandControllers=new ArrayList<CommandController>();
    commandControllers.add(new EngineController());
    commandControllers.add(new WingsController());
    Cockpit cockpit=new Cockpit(commandControllers);
    Pilot pilot = new Pilot(cockpit);
    pilot.fly();
    pilot.land();

  }

}
