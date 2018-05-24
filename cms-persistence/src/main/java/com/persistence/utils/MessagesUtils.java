package com.persistence.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class MessagesUtils
{

  private static MessagesUtils instance = new MessagesUtils();

  private Properties messages;
  private String filePath;

  private MessagesUtils()
  {
    init();
  }

  public static MessagesUtils getInstance()
  {
    return instance;
  }

  private void init()
  {
    messages = new Properties();
    filePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "messages.properties";
    try
    {
      messages.load(new FileReader(new File(filePath)));
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  public String getMessage(String key, String... params)
  {
    String message = messages.getProperty(key);
    if(StringUtils.isNotBlank(message) && params.length != 0)
    {
      return MessageFormat.format(message, StringUtils.join(params, ","));
    }
    return message.replaceAll("\\{[0-9]\\}", "");
  }

  public void refresh()
  {

    File file = new File(filePath);
    FileReader reader = null;
    if(file.canRead())
    {
      try
      {
        reader = new FileReader(file);
      }
      catch(FileNotFoundException e)
      {
        e.printStackTrace();
      }
    }
    if(reader != null)
    {
      messages.clear();
      try
      {
        messages.load(reader);
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
      finally
      {
        IOUtils.closeQuietly(reader);
      }
    }
  }
}
