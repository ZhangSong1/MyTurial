package com.persistence.model;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ExceptionResponse implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String code = String.valueOf(Status.BAD_REQUEST.getStatusCode());
  private String message;

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public void setCode(int code)
  {
    this.code = String.valueOf(code);
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

}
