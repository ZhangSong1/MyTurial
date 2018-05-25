package com.design.patterns.states;

public class Exec
{

  public static void main(String[] args)
  {
    Kid kid=new Kid(1);
    kid.eat();
    kid.play();
    kid.setAge(3);
    kid.eat();
    kid.play();
    kid.setAge(2);
    kid.eat();
    kid.play();

  }

}
