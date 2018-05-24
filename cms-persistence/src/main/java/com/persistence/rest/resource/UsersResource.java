package com.persistence.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.persistence.entity.UserEntity;
import com.persistence.rest.exception.CmsException;
import com.persistence.rest.exception.ErrorMessage;
import com.persistence.service.orm.UserService;
import com.persistence.utils.ValidationUtils;

@Component
@Path("users")
@Api(value = "users", tags = { "用户管理接口" })
public class UsersResource
{
  private UserService userService;

  @Autowired
  public UsersResource(UserService userService)
  {
    this.userService = userService;
  }

  @GET
  @Path("/email/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "获取用户", notes = "用户名必须非空", httpMethod = "GET")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "该用户不存在") })
  public Response getPwdByUsername(@ApiParam(name = "email", value = "电子邮箱", required = true) @PathParam(value = "email") String email)
  {
    Assert.notNull(email, ErrorMessage.BAD_REQUEST.getMessagekey());
    if(!ValidationUtils.isEmail(email))
    {
      throw new CmsException(ErrorMessage.BAD_REQUEST, email);
    }
    return Response.ok(userService.getUserByEmail(email)).build();

  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "新增用户", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON)
  @ApiResponses(value = { @ApiResponse(code = 201, message = "新增成功"), @ApiResponse(code = 409, message = "用户已存在"),
      @ApiResponse(code = 400, message = "非法参数"), @ApiResponse(code = 500, message = "新增失败") })
  public Response save(UserEntity user)
  {
    Assert.notNull(user.getEmail(), ErrorMessage.BAD_REQUEST.getMessagekey());
    Assert.notNull(user.getPassword(), ErrorMessage.BAD_REQUEST.getMessagekey());
    if(!ValidationUtils.isEmail(user.getEmail()))
    {
      throw new CmsException(ErrorMessage.BAD_REQUEST, user.getEmail());
    }
    if(!ValidationUtils.isAlphanumeric(user.getPassword()))
    {
      throw new CmsException(ErrorMessage.BAD_REQUEST, user.getPassword());
    }
    if(userService.exist(user.getEmail()))
    {
      throw new CmsException(ErrorMessage.DATA_EXIST, user.getEmail());
    }
    else
    {
      return Response.status(Status.CREATED).entity(userService.save(user)).build();
    }
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "修改密码", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 404, message = "该用户不存在"),
      @ApiResponse(code = 400, message = "非法参数"), @ApiResponse(code = 500, message = "修改失败") })
  public Response resetPwd(UserEntity user)
  {
    Assert.notNull(user.getEmail(), ErrorMessage.BAD_REQUEST.getMessagekey());
    Assert.notNull(user.getPassword(), ErrorMessage.BAD_REQUEST.getMessagekey());

    if(!ValidationUtils.isEmail(user.getEmail()))
    {
      throw new CmsException(ErrorMessage.BAD_REQUEST, user.getEmail());
    }
    if(!ValidationUtils.isAlphanumeric(user.getPassword()))
    {
      throw new CmsException(ErrorMessage.BAD_REQUEST, user.getPassword());
    }

    if(userService.exist(user.getEmail()))
    {
      throw new CmsException(ErrorMessage.NOT_FOUND, user.getEmail());
    }
    else
    {
      userService.resetPwd(user);
      return Response.status(Status.OK).build();
    }
  }
}
