package com.persistence.rest.exception;

public class BaseException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private ErrorMessage errorMessage;

  private String[] values;

  public BaseException()
  {
    super();
  }

  public BaseException(String message)
  {
    super(message);
  }

  public BaseException(ErrorMessage errorMessage, String... params)
  {
    this.errorMessage = errorMessage;
    values = new String[params.length];
    for(int i = 0; i < params.length; i++)
    {
      values[i] = params[i];
    }
  }

  public String[] getValues()
  {
    return values;
  }

  public void setValues(String[] values)
  {
    this.values = values;
  }

  public ErrorMessage getErrorMessage()
  {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage)
  {
    this.errorMessage = errorMessage;
  }

}
