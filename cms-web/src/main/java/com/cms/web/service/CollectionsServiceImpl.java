package com.cms.web.service;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cms.web.dto.CollectionsDto;
import com.cms.web.exception.CmsServiceException;
import com.cms.web.util.JSONUtils;
import com.cms.web.util.WebConstants;

@Service
public class CollectionsServiceImpl implements CollectionsService
{
  public final static Logger logger = LoggerFactory.getLogger(CollectionsServiceImpl.class);

  @Override
  public void save(CollectionsDto collections)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_FAVORITE_BASE_PATH);
    Response response = webTarget.request().buildPost(Entity.form(getForm(collections))).invoke();
    if(Status.CREATED.getStatusCode() != response.getStatusInfo().getStatusCode())
    {
      logger.error(response.readEntity(String.class));
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

  }

  @Override
  public List<CollectionsDto> queryAll()
  {
    JerseyWebTarget webTarget =
        JerseyClientBuilder.createClient().register(GzipInterceptor.class).target(WebConstants.REST_FAVORITE_BASE_PATH);
    Response response = webTarget.request().buildGet().invoke();
    if(Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      if(response.hasEntity())
      {
        return response.readEntity(new GenericType<List<CollectionsDto>>()
        {
        });
      }
      return null;
    }
    else
    {
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
  }

  @Override
  public void delete(int id)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_FAVORITE_ID_PATH + id);
    Response response = webTarget.request().buildDelete().invoke();
    if(Status.INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatusInfo().getStatusCode())
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());

  }

  private Form getForm(Object obj)
  {
    final Form form = new Form();
    JSONUtils.json2map(JSONUtils.obj2json(obj)).forEach((id, val) -> form.param(id, String.valueOf(val)));
    return form;
  }

}
