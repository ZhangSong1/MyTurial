package com.cms.web.model;

import java.io.Serializable;

public class CollectionsModel implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int id;
  private String name;
  private String path;
  private String imgData;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPath()
  {
    return path;
  }

  public void setPath(String path)
  {
    this.path = path;
  }

  public String getImgData()
  {
    return imgData;
  }

  public void setImgData(String imgData)
  {
    this.imgData = imgData;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

}
