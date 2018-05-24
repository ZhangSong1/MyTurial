package com.persistence.rest.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.persistence.rest.annotation.Compress;

@Provider
@Compress
public class CompressInterceptor implements WriterInterceptor
{

  @Override
  public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException
  {
    MultivaluedMap<String, Object> header = context.getHeaders();
    header.add("Content-Encoding", "gzip");
    final OutputStream outputStream = context.getOutputStream();
    context.setOutputStream(new GZIPOutputStream(outputStream));
    context.proceed();
  }

}
