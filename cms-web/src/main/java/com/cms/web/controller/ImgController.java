package com.cms.web.controller;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cms.web.facade.NewsFacade;
import com.cms.web.util.ImageHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response.Status;

@Controller
@RequestMapping("/img")
public class ImgController
{

  public final static Logger logger = LoggerFactory.getLogger(ImgController.class);

  private NewsFacade newsFacade;
  
  @Autowired
  public ImgController(NewsFacade newsFacade)
  {
    this.newsFacade = newsFacade;
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  @ResponseBody
  public LazyDynaBean upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response,
      HttpSession session)
  {
    LazyDynaBean msg = new LazyDynaBean();
    long size = file.getSize() / FileUtils.ONE_MB;
    logger.info("upload image {} ,size {}", file.getOriginalFilename(), size);
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    if(!ImageHelper.isImage(extension))
    {
      logger.info("upload  image {} fail,error extension {}", extension);
      msg.set("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
      msg.set("message", MessageFormat.format("error extension {0}", extension));

    }
    else
    {
      String name = FilenameUtils.getBaseName(file.getOriginalFilename());
      String savePath = StringUtils.join(request.getHeader("Origin"), "/image/", name, "_", System.currentTimeMillis(), ".", extension);
      try
      {
        if(newsFacade.saveImage(savePath, file.getBytes()))
        {
          msg.set("code", String.valueOf(Status.OK.getStatusCode()));
          msg.set("message", "upload success!");
          msg.set("url", savePath);
        }
      }
      catch(IOException e)
      {
        msg.set("code", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        msg.set("message", e.getMessage());
      }
    }
    return msg;
  }

  @RequestMapping(value = "/browser", method = RequestMethod.GET)
  public String browser()
  {
    return "news";
  }
}
