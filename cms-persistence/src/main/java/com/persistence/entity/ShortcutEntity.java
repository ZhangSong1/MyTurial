package com.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the shortcuts database table.
 * 
 */
@Entity
@Table(name = "shortcuts")
@XmlRootElement(name = "favorite")
public class ShortcutEntity implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Lob
  @FormParam("imgData")
  private String imgData;

  @FormParam("name")
  private String name;

  @FormParam("url")
  private String url;

  public ShortcutEntity()
  {
  }

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
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

}
