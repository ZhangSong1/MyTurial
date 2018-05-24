package org.cms.auth.exception;

public class UserNotFoundException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public UserNotFoundException()
  {
    this("user doesn't exist!");
  }

  public UserNotFoundException(String message)
  {
    super(message);
    // TODO Auto-generated constructor stub
  }

}
