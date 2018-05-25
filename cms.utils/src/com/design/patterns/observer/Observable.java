package com.design.patterns.observer;

public interface Observable
{
  public void addObserver(Observer observer);

  public void notifyObservers();

  public void removeObserver(Observer observer);
}
