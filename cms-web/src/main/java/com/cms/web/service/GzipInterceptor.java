package com.cms.web.service;

import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

public class GzipInterceptor implements ReaderInterceptor, WriterInterceptor
{

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException
  {
    List<String> header = context.getHeaders().get("Content-Encoding");
    if(header != null && header.contains("gzip")){
      context.setInputStream(new GZIPInputStream(context.getInputStream()));
    }
    return context.proceed();
  }

  @Override
  public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException
  {
    List header = context.getHeaders().get("Content-Encoding");
    if(header != null && header.contains("gzip")){
    context.setOutputStream(new GZIPOutputStream(context.getOutputStream()));
    context.getHeaders().add("Content-Encoding", "gzip");
    }
    context.proceed();

  }

}
