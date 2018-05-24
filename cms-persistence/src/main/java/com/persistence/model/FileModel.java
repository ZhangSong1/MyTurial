package com.persistence.model;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "file")
public class FileModel implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @FormParam("url")
  private String url;
  @FormParam("data")
  private Object data;

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public Object getData()
  {
    return data;
  }

  public void setData(Object data)
  {
    this.data = data;
  }

}
