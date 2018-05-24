package com.persistence.service.file;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService
{

  @Override
  public boolean save(String url, Object data)
  {
    JerseyClient client = JerseyClientBuilder.createClient();
    JerseyWebTarget webTarget = client.target(url);
    webTarget.request().buildDelete().invoke();
    Response response = webTarget.request().buildPost(Entity.entity(data, MediaType.TEXT_HTML)).invoke();
    return Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode();
  }

  @Override
  public String getHtmlContet(String url)
  {
    JerseyClient client = JerseyClientBuilder.createClient();
    JerseyWebTarget webTarget = client.target(url);
    Response response = webTarget.request().buildGet().invoke();
    return (String)response.getEntity();
  }

}
