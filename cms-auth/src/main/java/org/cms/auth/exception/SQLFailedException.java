package org.cms.auth.exception;

public class SQLFailedException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public SQLFailedException()
  {
    this("SQL excute failed!");
  }

  public SQLFailedException(String message)
  {
    super(message);
    // TODO Auto-generated constructor stub
  }
}
