package com.persistence.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.persistence.service.orm.RoleService;

@Component
@Path("role")
@Api(value = "role", tags = { "角色管理接口" })
public class RoleResource
{
  private RoleService roleService;

  @Autowired
  public RoleResource(RoleService roleService)
  {
    this.roleService = roleService;
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "获取所有角色", httpMethod = "GET")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK")})
  public Response getAllRoles()
  {
    return Response.ok(roleService.findAll()).build();

  }
  
}
