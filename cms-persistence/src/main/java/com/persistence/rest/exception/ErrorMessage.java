package com.persistence.rest.exception;

import javax.ws.rs.core.Response.Status;

import com.persistence.rest.config.MessagesConstants;

public enum ErrorMessage
{
  BAD_REQUEST(Status.BAD_REQUEST.getStatusCode(), "CMS400", MessagesConstants.BAD_PARAMETERS),
  NOT_FOUND(Status.NOT_FOUND.getStatusCode(), "CMS404", MessagesConstants.NOT_FOUND),
  DATA_EXIST(Status.CONFLICT.getStatusCode(), "CMS409", MessagesConstants.NOT_FOUND),
  UNSUPPORTED_MEDIA_TYPE(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),"CM415",MessagesConstants.UNSUPPORTED_MEDIA_TYPE),
  INTERNAL_SERVER_ERROR(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "CMS500", MessagesConstants.INTERNAL_SERVER_ERROR);
  private int statusCode;
  private String cmsCode;
  private String messagekey;

  ErrorMessage(int statusCode, String cmsCode, String messagekey)
  {
    this.statusCode = statusCode;
    this.cmsCode = cmsCode;
    this.messagekey = messagekey;
  }

  public int getStatusCode()
  {
    return statusCode;
  }

  public void setStatusCode(int statusCode)
  {
    this.statusCode = statusCode;
  }

  public String getCmsCode()
  {
    return cmsCode;
  }

  public void setCmsCode(String cmsCode)
  {
    this.cmsCode = cmsCode;
  }

  public String getMessagekey()
  {
    return messagekey;
  }

  public void setMessagekey(String messagekey)
  {
    this.messagekey = messagekey;
  }

}
