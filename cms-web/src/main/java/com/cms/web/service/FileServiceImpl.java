package com.cms.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.springframework.stereotype.Service;

import com.cms.web.util.WebConstants;
@Service
public class FileServiceImpl implements FileService
{

  @Override
  public boolean save(String filePath, String content)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_NEWS_BASE_PATH);
    Map<String, String> map = new HashMap<String, String>();
    map.put("url", filePath);
    map.put("data", content);
    Response response = webTarget.request().buildPut(Entity.entity(map, MediaType.APPLICATION_FORM_URLENCODED)).invoke();
    if(Status.CREATED.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      return true;
    }
    return false;
  }

  @Override
  public String getContet(String filePath)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_NEWS_BASE_PATH);
    Map<String, String> map = new HashMap<String, String>();
    map.put("url", filePath);
    Response response = webTarget.request().build("GET", Entity.entity(map, MediaType.APPLICATION_JSON)).invoke();
    if(Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      return (String)response.getEntity();
    }
    return null;
  }

}
