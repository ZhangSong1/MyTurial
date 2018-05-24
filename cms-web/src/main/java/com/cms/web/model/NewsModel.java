package com.cms.web.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.cms.web.util.WebConstants;

public class NewsModel implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String title;
  private NewsType type;
  private String content;
  private String author;
  private String createDate = DateFormatUtils.format(new Date(), WebConstants.DATE_FORMAT);
  private String updateDate;
  private NewsStatus status;
  private String url;
  private String doc;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public NewsType getType()
  {
    return type;
  }

  public void setType(NewsType type)
  {
    this.type = type;
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public String getCreateDate()
  {
    return createDate;
  }

  public void setCreateDate(String createDate)
  {
    this.createDate = createDate;
  }

  public NewsStatus getStatus()
  {
    return status;
  }

  public void setStatus(NewsStatus status)
  {
    this.status = status;
  }

  public String getUpdateDate()
  {
    return updateDate;
  }

  public void setUpdateDate(String updateDate)
  {
    this.updateDate = updateDate;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getDoc()
  {
    return doc;
  }

  public void setDoc(String doc)
  {
    this.doc = doc;
  }

}
