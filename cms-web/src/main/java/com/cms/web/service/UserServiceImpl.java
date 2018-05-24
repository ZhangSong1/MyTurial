package com.cms.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.springframework.stereotype.Service;

import com.cms.web.dto.CollectionsDto;
import com.cms.web.dto.RoleDto;
import com.cms.web.dto.UserDto;
import com.cms.web.exception.CmsServiceException;
import com.cms.web.facade.adapter.UserAdapter;
import com.cms.web.model.Role;
import com.cms.web.model.User;
import com.cms.web.util.WebConstants;

@Service
public class UserServiceImpl implements UserService
{

  @Override
  public String getPwdByUsername(String name)
  {
    return getUserByUsername(name).getPassword();
  }

  @Override
  public void save(User user)
  {
    user.setPassword(DigestUtils.md5Hex(user.getPassword()));
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_USER_BASE_PATH);
    Response response = webTarget.request().buildPost(Entity.entity(user, MediaType.APPLICATION_JSON)).invoke();
    if(Status.CREATED.getStatusCode() != response.getStatusInfo().getStatusCode())
    {
      if(Status.CONFLICT.getStatusCode() == response.getStatusInfo().getStatusCode())
      {
        throw new CmsServiceException("用户已存在!");
      }
      throw new CmsServiceException("创建失败!");
    }
    user.setId(Integer.valueOf(response.readEntity(String.class)));
  }

  @Override
  public User getUserByUsername(String username)
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_USER_NAME_PATH + username);
    Response response = webTarget.request().buildGet().invoke();
    if(Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      UserDto result = response.readEntity(UserDto.class);
      return result != null ? UserAdapter.convertToUser(result) : null;
    }
    if(Status.NOT_FOUND.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      throw new CmsServiceException("用户不存在!");
    }
    throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  @Override
  public List<Role> getAllRoles()
  {
    JerseyWebTarget webTarget = JerseyClientBuilder.createClient().target(WebConstants.REST_ROLE_BASE_PATH);
    Response response = webTarget.request().buildGet().invoke();
    if(Status.OK.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      List<RoleDto> result = response.readEntity(new GenericType<List<RoleDto>>(){});
      return result != null ? UserAdapter.convertToRole(result) : new ArrayList<Role>() ;
    }
    if(Status.NOT_FOUND.getStatusCode() == response.getStatusInfo().getStatusCode())
    {
      throw new CmsServiceException("用户不存在!");
    }
    throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }
  
  
}
