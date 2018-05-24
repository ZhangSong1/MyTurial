package com.cms.web.dto;

import java.io.Serializable;

public class CollectionsDto implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int id;
  private String imgData;
  private String name;
  private String url;
  public int getId()
  {
    return id;
  }
  public void setId(int id)
  {
    this.id = id;
  }
  public String getImgData()
  {
    return imgData;
  }
  public void setImgData(String imgData)
  {
    this.imgData = imgData;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getUrl()
  {
    return url;
  }
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  
}
