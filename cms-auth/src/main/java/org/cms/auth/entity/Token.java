package org.cms.auth.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Token implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String series;

  private Timestamp lastUsed;

  private String token;

  private String username;

  public Token()
  {
  }

  public String getSeries()
  {
    return this.series;
  }

  public void setSeries(String series)
  {
    this.series = series;
  }

  public Timestamp getLastUsed()
  {
    return this.lastUsed;
  }

  public void setLastUsed(Timestamp lastUsed)
  {
    this.lastUsed = lastUsed;
  }

  public String getToken()
  {
    return this.token;
  }

  public void setToken(String token)
  {
    this.token = token;
  }

  public String getUsername()
  {
    return this.username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}
