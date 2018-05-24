package org.cms.auth.exception;

public class InvalidDataException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public InvalidDataException()
  {
    this("Please make sure the data is right.");
  }

  public InvalidDataException(String message)
  {
    super(message);
    // TODO Auto-generated constructor stub
  }
}
