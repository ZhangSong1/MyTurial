package com.cms.web.service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.springframework.stereotype.Service;

import com.cms.web.dto.MyPageDto;
import com.cms.web.dto.NewsDto;
import com.cms.web.exception.CmsServiceException;
import com.cms.web.util.WebConstants;

@Service
public class NewsServiceImpl implements NewsService
{

  @Override
  public void save(NewsDto news)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_NEWS_BASE_PATH);
    Response response = webTarget.request().buildPost(Entity.entity(news, MediaType.APPLICATION_FORM_URLENCODED)).invoke();
    if(Status.CREATED.getStatusCode() != response.getStatusInfo().getStatusCode())
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  @Override
  public NewsDto findByDoc(String doc)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_NEWS_NAME_PATH + doc);
    Response response = webTarget.request().buildGet().invoke();
    if(Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      if(response.hasEntity())
      {
        return response.readEntity(NewsDto.class);
      }
      else
      {
        throw new CmsServiceException(Status.NOT_FOUND.getReasonPhrase());
      }
    }
    else
    {
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

  }

  @Override
  public void deleteByDoc(String doc)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_NEWS_NAME_PATH + doc);
    Response response = webTarget.request().buildDelete().invoke();
    if(Status.INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatusInfo().getStatusCode())
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  @Override
  public MyPageDto getPageableNews(int pageIndex, int pageSize)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().register(GzipInterceptor.class).target(WebConstants.REST_NEWS_BASE_PATH);
    Response response = webTarget.request().buildGet().property("offset", pageIndex).property("limit", pageSize).invoke();
    if(Status.INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatusInfo().getStatusCode())
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    if(Status.BAD_REQUEST.getStatusCode() == response.getStatusInfo().getStatusCode())
      throw new CmsServiceException(Status.BAD_REQUEST.getReasonPhrase());
    return response.readEntity(MyPageDto.class);
  }

}
