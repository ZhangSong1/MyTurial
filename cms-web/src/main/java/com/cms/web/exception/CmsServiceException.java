package com.cms.web.exception;

public class CmsServiceException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  String message;

  public CmsServiceException(String message)
  {
    super();
    this.message = message;
  }

  @Override
  public String getMessage()
  {
    return message;
  }

}
