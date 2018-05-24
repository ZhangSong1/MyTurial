package com.cms.web.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cms.web.facade.CollectionsFacade;
import com.cms.web.model.CollectionsModel;
import com.cms.web.util.ImageHelper;
import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;

@Controller
@RequestMapping(WebConstants.COLLECTIONS_PATH)
public class CollectionsController
{
  public final static Logger logger = LoggerFactory.getLogger(CollectionsController.class);

  private CollectionsFacade collectionsFacade;

  @Autowired
  public CollectionsController(CollectionsFacade collectionsFacade)
  {
    this.collectionsFacade = collectionsFacade;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView main(HttpServletRequest request, HttpServletResponse resopnse)
  {
    ModelAndView views = new ModelAndView(WebUtils.getJsp("collections"));
    List<CollectionsModel> list = collectionsFacade.queryAll();
    views.addObject("collectionsList", list);
    views.addObject("total", list.size());
    return views;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public LazyDynaBean upload(@RequestParam("file") MultipartFile file, @ModelAttribute CollectionsModel model, HttpServletRequest request,
      HttpServletResponse response, HttpSession session)
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
      try
      {
        model.setImgData(ImageHelper.getImageWithBase64(file.getInputStream(), extension));
      }
      catch(IOException e)
      {
        msg.set("code", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        msg.set("message", "create fail!");
      }
      collectionsFacade.save(model);
      msg.set("code", String.valueOf(Status.OK.getStatusCode()));
      msg.set("message", "create success!");
    }
    return msg;
  }
  
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  @ResponseBody
  public LazyDynaBean delete(@PathVariable int id){
    LazyDynaBean msg = new LazyDynaBean();
    collectionsFacade.delete(id);
    msg.set("code", String.valueOf(Status.OK.getStatusCode()));
    msg.set("message", "detele success!");
    return msg;
  }

}
