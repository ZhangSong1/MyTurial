package com.cms.web.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class NewsDto implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Timestamp createDate;
  private String name;
  private String realPath;
  private String status;
  private String title;
  private String type;
  private String author;
  private Date updateDate;
  public Timestamp getCreateDate()
  {
    return createDate;
  }
  public void setCreateDate(Timestamp createDate)
  {
    this.createDate = createDate;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getRealPath()
  {
    return realPath;
  }
  public void setRealPath(String realPath)
  {
    this.realPath = realPath;
  }
  public String getStatus()
  {
    return status;
  }
  public void setStatus(String status)
  {
    this.status = status;
  }
  public String getTitle()
  {
    return title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  public String getType()
  {
    return type;
  }
  public void setType(String type)
  {
    this.type = type;
  }
  public String getAuthor()
  {
    return author;
  }
  public void setAuthor(String author)
  {
    this.author = author;
  }
  public Date getUpdateDate()
  {
    return updateDate;
  }
  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }
  
  
}
