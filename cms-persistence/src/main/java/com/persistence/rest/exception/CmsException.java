package com.persistence.rest.exception;

public class CmsException extends BaseException
{

  private static final long serialVersionUID = 1L;

  public CmsException()
  {
    super();
  }

  public CmsException(ErrorMessage message, String... params)
  {
    super(message,params);
  }
  
}
