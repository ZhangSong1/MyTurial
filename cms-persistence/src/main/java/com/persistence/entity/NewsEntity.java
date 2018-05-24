package com.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.sql.Timestamp;

/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name = "NEWS")
@XmlRootElement(name = "news")
public class NewsEntity implements Serializable
{
  private static final long serialVersionUID = 1L;
  @FormParam("createDate")
  private Timestamp createDate;
  @FormParam("name")
  private String name;
  @FormParam("realPath")
  private String realPath;
  @FormParam("status")
  private String status;
  @FormParam("title")
  private String title;
  @FormParam("type")
  private String type;
  @FormParam("author")
  private String author;
  @FormParam("updateDate")
  private Date updateDate;

  public NewsEntity()
  {
  }

  // JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
  // TABLE：使用一个特定的数据库表格来保存主键。
  // SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
  // IDENTITY：主键由数据库自动生成（主要是自动增长型）
  // AUTO：主键由程序控制。

  @Column(name = "CREATE_DATE")
  public Timestamp getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Timestamp createDate)
  {
    this.createDate = createDate;
  }

  @Id
  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Column(name = "REAL_PATH")
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
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATE_DATE")
  public Date getUpdateDate()
  {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

}
