package com.persistence.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.persistence.entity.ShortcutEntity;
import com.persistence.rest.annotation.Compress;
import com.persistence.service.orm.ShortcutService;

@Component
@Path("favorite")
@Api(value = "favorite", tags = { "快捷方式管理接口" })
public class FavoriteResource
{

  private ShortcutService shortcutService;

  @Autowired
  public FavoriteResource(ShortcutService shortcutService)
  {
    this.shortcutService = shortcutService;
  }

  @DELETE
  @Path("/id/{id:[0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "删除快捷方式", notes = "根据id删除", httpMethod = "DELETE")
  @ApiResponses(value = { @ApiResponse(code = 204, message = "删除成功"), @ApiResponse(code = 400, message = "参数非法") })
  public Response delete(@ApiParam(name = "id", value = "ID", required = true) @PathParam("id") int id)
  {
    if(id < 1)
      throw new IllegalArgumentException("id");
    shortcutService.delete(id);
    return Response.noContent().build();
  }

  @GET
  @Compress
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查询", notes = "查询所有", httpMethod = "GET")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功") })
  public Response queryAll()
  {
    return Response.ok(shortcutService.queryAll()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @ApiOperation(value = "保存快捷方式", httpMethod = "POST", consumes = MediaType.APPLICATION_FORM_URLENCODED)
  @ApiImplicitParams(value = { @ApiImplicitParam(name = "imgData", value = "图片二进制数据", required = true, paramType = "form"),
      @ApiImplicitParam(name = "name", value = "快捷方式名称", required = true, paramType = "form"),
      @ApiImplicitParam(name = "url", value = "快捷方式url", required = true, paramType = "form") })
  @ApiResponses(value = { @ApiResponse(code = 201, message = "保存成功"), @ApiResponse(code = 500, message = "保存失败"),
      @ApiResponse(code = 400, message = "参数非法") })
  public Response save(MultivaluedMap<String, String> formParams)
  {
    Assert.notNull(formParams.getFirst("imgData"), "imgData");
    Assert.notNull(formParams.getFirst("name"), "name");
    Assert.notNull(formParams.getFirst("url"), "url");
    ShortcutEntity favorite=new ShortcutEntity();
    favorite.setImgData(formParams.getFirst("imgData"));
    favorite.setName(formParams.getFirst("name"));
    favorite.setUrl(formParams.getFirst("url"));
    if(shortcutService.save(favorite) > 0)
    {
      return Response.status(Status.CREATED).build();
    }
    else
    {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

}
