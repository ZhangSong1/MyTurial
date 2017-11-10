package com.design.patterns.commands;

public interface CommandController
{
  public void excute(String command);

  public boolean canHandle(String command);

}
