package com.design.patterns.commands;

import java.util.List;

/*
 * 1.Applied where there are many controls exits.
 * 2.Simplifies and encapsulates the receivers.
 * 3.Enable to add more command controls.
 * 
 在下面的情况下应当考虑使用命令模式：

1、使用命令模式作为"CallBack"在面向对象系统中的替代。"CallBack"讲的便是先将一个函数登记上，然后在以后调用此函数。

2、需要在不同的时间指定请求、将请求排队。一个命令对象和原先的请求发出者可以有不同的生命期。换言之，原先的请求发出者可能已经不在了，而命令对象本身仍然是活动的。这时命令的接收者可以是在本地，也可以在网络的另外一个地址。命令对象可以在串形化之后传送到另外一台机器上去。

3、系统需要支持命令的撤消(undo)。命令对象可以把状态存储起来，等到客户端需要撤销命令所产生的效果时，可以调用undo()方法，把命令所产生的效果撤销掉。命令对象还可以提供redo()方法，以供客户端在需要时，再重新实施命令效果。

4、如果一个系统要将系统中所有的数据更新到日志里，以便在系统崩溃时，可以根据日志里读回所有的数据更新命令，重新调用Execute()方法一条一条执行这些命令，从而恢复系统在崩溃前所做的数据更新。

5、一个系统需要支持交易(Transaction)。一个交易结构封装了一组数据更新命令。使用命令模式来实现交易结构可以使系统增加新的交易类型。
 */

public class Cockpit implements CommandController
{

  List<CommandController> commandControllers;

  public final static String TAKE_OFF = "take_off";
  public final static String LAND = "land";

  public Cockpit(List<CommandController> commandControllers)
  {
    commandControllers.add(this);
    this.commandControllers = commandControllers;
  }

  @Override
  public void excute(String command)
  {

    switch(command)
    {
    case TAKE_OFF:
      takeOff();
      break;
    case LAND:
      land();
      break;
    }

    commandControllers.stream().filter(commandController -> !(commandController instanceof Cockpit)).forEach((commandController) -> {
      if(commandController.canHandle(command))
        commandController.excute(command);
    });

  }

  @Override
  public boolean canHandle(String command)
  {
    return commandControllers.stream().filter(commandController -> commandController.canHandle(command)).count() > 0;
  }

  private void takeOff()
  {
    this.excute(WingsController.MOVE_UP);
    this.excute(EngineController.SPEED_UP);
  }

  private void land()
  {
    this.excute(EngineController.SLOW_DOWN);
    this.excute(WingsController.MOVE_DOWN);

  }

}
