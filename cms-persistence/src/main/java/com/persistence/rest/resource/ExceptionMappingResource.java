package com.persistence.rest.resource;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.persistence.model.ExceptionResponse;
import com.persistence.rest.exception.BaseException;
import com.persistence.rest.exception.CmsException;
import com.persistence.rest.exception.ErrorMessage;
import com.persistence.utils.MessagesUtils;

@Provider
public class ExceptionMappingResource implements ExceptionMapper<Exception>
{

  @Override
  public Response toResponse(Exception exception)
  {
    ResponseBuilder responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
    ExceptionResponse response = null;

    if(exception instanceof IllegalArgumentException)
    {
      response = setResponse(ErrorMessage.BAD_REQUEST, exception.getMessage());
      responseBuilder.status(Status.BAD_REQUEST);
    }

    if(exception instanceof NotFoundException)
    {
      response = setResponse(ErrorMessage.NOT_FOUND, exception.getMessage());
      responseBuilder.status(Status.BAD_REQUEST);
    }

    if(exception instanceof CmsException)
    {
      BaseException myException = (CmsException)exception;
      response = setResponse(myException.getErrorMessage(), myException.getValues());
      responseBuilder.status(myException.getErrorMessage().getStatusCode());
    }
    
    if(exception instanceof NotSupportedException){
      response = setResponse(ErrorMessage.UNSUPPORTED_MEDIA_TYPE, exception.getMessage());
      responseBuilder.status(Status.UNSUPPORTED_MEDIA_TYPE);
    }

    if(response == null){
      response = setResponse(ErrorMessage.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
    return responseBuilder.entity(response).build();
  }

  private ExceptionResponse setResponse(ErrorMessage errorMessage, String... params)
  {
    ExceptionResponse response = new ExceptionResponse();
    response.setCode(errorMessage.getCmsCode());
    response.setMessage(MessagesUtils.getInstance().getMessage(errorMessage.getMessagekey(), params));
    return response;
  }

}
