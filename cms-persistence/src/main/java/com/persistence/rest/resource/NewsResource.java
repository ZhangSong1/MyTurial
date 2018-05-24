package com.persistence.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.security.InvalidParameterException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.persistence.entity.NewsEntity;
import com.persistence.rest.annotation.Compress;
import com.persistence.service.orm.NewsService;

@Component
@Path("news")
@Api(value = "news", tags = { "新闻管理接口" })
public class NewsResource
{
  private NewsService newsService;

  @Autowired
  public NewsResource(NewsService newsService)
  {
    this.newsService = newsService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @ApiOperation(value = "保存新闻", httpMethod = "POST", consumes = MediaType.APPLICATION_FORM_URLENCODED)
  @ApiResponses(value = { @ApiResponse(code = 201, message = "保存成功"), @ApiResponse(code = 500, message = "保存失败") })
  public Response save(NewsEntity news)
  {
    if(StringUtils.isNotBlank(newsService.save(news)))
    {
      return Response.status(Status.CREATED).build();
    }
    else
    {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Compress
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "查询新闻", notes = "分页查询", httpMethod = "GET")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 400, message = "非法参数") })
  public Response queryPageableNews(@ApiParam(name = "offset", value = "起始位置") @DefaultValue("0") @QueryParam(value = "offset") int offset,
      @ApiParam(name = "limit", value = "查询记录条数") @DefaultValue("10") @QueryParam(value = "limit") int limit)
  {
    if(offset < 0 || limit < 1)
    {
      throw new InvalidParameterException(StringUtils.join("offset", ",", "limit"));
    }
    return Response.ok(newsService.queryPageableNews(offset, limit)).build();
  }

  @DELETE
  @Path("/name/{name}")
  @ApiOperation(value = "删除新闻", notes = "根据新闻名称删除", httpMethod = "DELETE")
  @ApiResponses(value = { @ApiResponse(code = 204, message = "删除成功") })
  public Response deleteByName(@ApiParam(name = "name", value = "新闻名称", required = true) @PathParam("name") String name)
  {
    Assert.notNull(name, "name");
    newsService.deleteByName(name);
    return Response.noContent().build();
  }

  @GET
  @Path("/name/{name}")
  @ApiOperation(value = "查询新闻", notes = "根据新闻名称查询", httpMethod = "GET")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功") })
  public Response findByName(@ApiParam(name = "name", value = "新闻名称", required = true) @PathParam("name") String name)
  {
    Assert.notNull(name, "name");
    return Response.ok(newsService.findByName(name)).build();
  }
}
