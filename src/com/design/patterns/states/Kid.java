package com.design.patterns.states;

/*
 * 1.Encapsulate the changing behaviour
 * 2.Any changes to the behaviours based on state will only change state objects
 * 3.Easy for extensions as we can create any number of State objects
 */
public class Kid
{
  private int age;

  private KidState kidState;

  public Kid(int age)
  {
    this.age = age;
    setAge(this.age);
  }

  public void play()
  {
    kidState.play();
  }

  public void eat()
  {
    kidState.eat();
  }

  public void setAge(int age)
  {
    this.age = age;
    switch(age)
    {
    case 1:
      kidState = new FirstYearKidState();
      break;
    case 2:
      kidState = new SecondYearKidState();
      break;
    case 3:
      kidState = new ThirdYearKidState();
      break;
    default:
      kidState = new FirstYearKidState();
      break;
    }
  }

}
