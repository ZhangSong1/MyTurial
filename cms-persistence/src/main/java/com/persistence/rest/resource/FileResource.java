package com.persistence.rest.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.persistence.model.FileModel;
import com.persistence.rest.annotation.Compress;
import com.persistence.rest.exception.CmsException;
import com.persistence.rest.exception.ErrorMessage;
import com.persistence.service.file.FileService;

@Component
@Path("file")
@Api(value = "file", tags = { "文件操作接口" })
public class FileResource
{
  private FileService fileService;

  @PUT
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @ApiOperation(value = "保存文件", notes = "保存文件", httpMethod = "PUT", consumes = MediaType.APPLICATION_FORM_URLENCODED)
  @ApiImplicitParams(value = { @ApiImplicitParam(name = "url", value = "文件path", required = true, paramType = "form"),
      @ApiImplicitParam(name = "data", value = "文件二进制数据", required = true, paramType = "form") })
  @ApiResponses(value = { @ApiResponse(code = 201, message = "保存成功"), @ApiResponse(code = 500, message = "保存失败") })
  public Response save(FileModel file)
  {
    Assert.notNull(file.getUrl(), "url");
    Assert.notNull(file.getData(), "data");
    if(fileService.save(file.getUrl(), file.getData()))
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
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_HTML)
  @ApiOperation(value = "查询文件", notes = "返回二进制数据", httpMethod = "GET", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_HTML)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "保存成功"), @ApiResponse(code = 404, message = "文件不存在") })
  public Response getHtmlContet(FileModel file)
  {
    Assert.notNull(file.getUrl(), ErrorMessage.BAD_REQUEST.getMessagekey());

    String data = fileService.getHtmlContet(file.getUrl());
    if(StringUtils.isNotBlank(data))
    {
      return Response.ok(Entity.entity(data, MediaType.TEXT_HTML)).build();
    }
    else
    {
      throw new CmsException(ErrorMessage.NOT_FOUND, file.getUrl());
    }
  }
}
