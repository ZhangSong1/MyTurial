package com.design.patterns.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Stock implements Observable
{
  private List<Observer> observers = null;

  private String name;
  private int price;

  public Stock()
  {
    this.observers = new ArrayList<Observer>();
  }

  @Override
  public void addObserver(Observer observer)
  {
    observers.add(observer);

  }

  @Override
  public void notifyObservers()
  {
    Iterator<Observer> it = observers.iterator();
    while(it.hasNext())
    {
      it.next().updateStock(this);
    }
  }

  @Override
  public void removeObserver(Observer observer)
  {
    observers.remove(observer);

  }

  public void updatePrice(int price)
  {
    this.price = price;
    this.notifyObservers();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getPrice()
  {
    return price;
  }

}
